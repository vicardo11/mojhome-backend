package it.sosinski.finances.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;

import it.sosinski.finances.model.FinanceDto;
import it.sosinski.finances.repository.FinanceRepository;
import it.sosinski.finances.repository.entity.FinanceEntity;
import it.sosinski.finances.service.mapper.FinanceMapper;

@MockitoSettings
class FinanceServiceImplTest {

	private static final String USER_ID = "userId";

	@Mock
	private FinanceRepository financeRepository;

	@Spy
	private FinanceMapper financeMapper;

	@InjectMocks
	private FinanceServiceImpl systemUnderTest;

	@Test
	void shouldReturnFinanceDtoList() {
		// Given
		when(financeRepository.findAllByUserId(USER_ID)).thenReturn(createFinanceEntityList());

		// When
		final List<FinanceDto> result = systemUnderTest.getFinancesList(USER_ID);

		// Then
		assertThat(result)
				.isNotNull()
				.hasSize(1);
	}

	private List<FinanceEntity> createFinanceEntityList() {
		return List.of(new FinanceEntity());
	}

}