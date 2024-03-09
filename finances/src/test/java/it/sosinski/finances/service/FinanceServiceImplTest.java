package it.sosinski.finances.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;

import it.sosinski.finances.exception.model.FinanceNotFoundException;
import it.sosinski.finances.exception.model.UserMismatchException;
import it.sosinski.finances.model.FinanceDto;
import it.sosinski.finances.model.FinanceType;
import it.sosinski.finances.repository.FinanceRepository;
import it.sosinski.finances.repository.entity.FinanceEntity;
import it.sosinski.finances.service.mapper.FinanceMapper;

@MockitoSettings
class FinanceServiceImplTest {

	private static final String USER_ID = "userId";

	private static final String ID = "1L";

	private static final String NAME = "Groceries";

	private static final BigDecimal AMOUNT = BigDecimal.valueOf(50.25);

	private static final LocalDate LOCAL_DATE = LocalDate.parse("2021-01-01");

	private static final BigDecimal NEW_AMOUNT = BigDecimal.valueOf(1047.78);

	@Mock
	private FinanceRepository financeRepository;

	@Spy
	private FinanceMapper financeMapper = FinanceMapper.INSTANCE;

	@InjectMocks
	private FinanceServiceImpl systemUnderTest;

	@Test
	void shouldReturnFinanceDtoList() {
		// Given
		when(financeRepository.findAllByUserId(USER_ID)).thenReturn(createFinanceEntityList());

		// When
		final List<FinanceDto> result = systemUnderTest.getAll(USER_ID);

		// Then
		assertThat(result)
				.isNotNull()
				.hasSize(1);
	}

	@Test
	void shouldUpdateRecord() {
		// Given
		final FinanceDto financeDto = createFinanceDto();
		final FinanceEntity existingFinanceEntity = createFinanceEntity();
		final FinanceEntity financeEntityToSave = createFinanceEntity();
		final FinanceEntity savedFinanceEntity = createFinanceEntity();
		savedFinanceEntity.setAmount(NEW_AMOUNT);
		when(financeRepository.findById(ID)).thenReturn(java.util.Optional.of(existingFinanceEntity));
		when(financeMapper.toFinanceEntity(financeDto, USER_ID)).thenReturn(financeEntityToSave);
		when(financeRepository.save(financeEntityToSave)).thenReturn(savedFinanceEntity);

		// When
		final FinanceDto result = systemUnderTest.update(financeDto, USER_ID);

		// Then
		assertThat(result)
				.extracting(
						FinanceDto::id,
						FinanceDto::name,
						FinanceDto::amount,
						FinanceDto::type,
						FinanceDto::date)
				.containsExactly(
						ID,
						NAME,
						NEW_AMOUNT,
						FinanceType.EXPENSE,
						LOCAL_DATE);
	}

	@Test
	void shouldThrowFinanceNotFoundExceptionWhenFinanceEntityNotFound() {
		// Given
		final FinanceDto financeDto = createFinanceDto();
		when(financeRepository.findById(financeDto.id())).thenReturn(Optional.empty());

		// When/Then
		assertThatThrownBy(() -> systemUnderTest.update(financeDto, USER_ID))
				.isInstanceOf(FinanceNotFoundException.class);
	}

	@Test
	void shouldThrowUserMismatchExceptionWhenUserDoesNotMatch() {
		// Given
		final FinanceDto financeDto = createFinanceDto();
		final FinanceEntity existingFinanceEntity = createFinanceEntity();
		existingFinanceEntity.setUserId("otherUserId");
		when(financeRepository.findById(financeDto.id())).thenReturn(Optional.of(existingFinanceEntity));

		// When/Then
		assertThatThrownBy(() -> systemUnderTest.update(financeDto, USER_ID))
				.isInstanceOf(UserMismatchException.class);
	}

	@Test
	void shouldCreateFinance() {
		// Given
		final FinanceDto financeDto = createFinanceDto();
		final FinanceEntity financeEntityToSave = createFinanceEntity();
		final FinanceEntity savedFinanceEntity = createFinanceEntity();
		when(financeMapper.toFinanceEntity(financeDto, USER_ID)).thenReturn(financeEntityToSave);
		when(financeRepository.save(financeEntityToSave)).thenReturn(savedFinanceEntity);

		// When
		final FinanceDto result = systemUnderTest.create(financeDto, USER_ID);

		// Then
		assertThat(result)
				.extracting(
						FinanceDto::id,
						FinanceDto::name,
						FinanceDto::amount,
						FinanceDto::type,
						FinanceDto::date)
				.containsExactly(
						ID,
						NAME,
						AMOUNT,
						FinanceType.EXPENSE,
						LOCAL_DATE);
	}

	@Test
	void shouldDeleteFinanceWhenUserIsOwner() {
		// Given
		final FinanceEntity financeEntity = new FinanceEntity(ID, USER_ID, NAME, FinanceType.EXPENSE, AMOUNT, LOCAL_DATE);
		when(financeRepository.findById(ID)).thenReturn(Optional.of(financeEntity));

		// When
		systemUnderTest.delete(ID, USER_ID);

		// Then
		verify(financeRepository).deleteById(ID);
	}

	@Test
	void shouldThrowFinanceNotFoundExceptionWhenFinanceDoesNotExist() {
		// Given
		when(financeRepository.findById(ID)).thenReturn(Optional.empty());

		// When/Then
		assertThatThrownBy(() -> systemUnderTest.delete(ID, USER_ID))
				.isInstanceOf(FinanceNotFoundException.class);
	}

	@Test
	void shouldThrowUserMismatchExceptionWhenUserIsNotOwner() {
		// Given
		final FinanceEntity financeEntity = new FinanceEntity(ID, "otherUserId", NAME, FinanceType.EXPENSE, AMOUNT, LOCAL_DATE);
		when(financeRepository.findById(ID)).thenReturn(Optional.of(financeEntity));

		// When/Then
		assertThatThrownBy(() -> systemUnderTest.delete(ID, USER_ID))
				.isInstanceOf(UserMismatchException.class);
	}

	private FinanceEntity createFinanceEntity() {
		return new FinanceEntity(ID, USER_ID, NAME, FinanceType.EXPENSE, AMOUNT, LOCAL_DATE);
	}

	private FinanceDto createFinanceDto() {
		return FinanceDto.builder()
				.id(ID)
				.name(NAME)
				.amount(AMOUNT)
				.type(FinanceType.EXPENSE)
				.date(LOCAL_DATE)
				.build();
	}

	private List<FinanceEntity> createFinanceEntityList() {
		return List.of(new FinanceEntity());
	}

}