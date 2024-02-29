package it.sosinski.finances.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.sosinski.finances.model.FinanceDto;
import it.sosinski.finances.model.FinanceType;
import it.sosinski.finances.service.FinanceService;

@MockitoSettings
class FinanceControllerTest {

	private static final String USER_ID = "userId";

	private static final String ID = "1";

	private static final String NAME = "Groceries";

	private static final BigDecimal AMOUNT = BigDecimal.valueOf(50.25);

	private static final LocalDate LOCAL_DATE = LocalDate.parse("2021-01-01");

	private final Principal PRINCIPAL = () -> USER_ID;

	@Mock
	private FinanceService financeService;

	@InjectMocks
	private FinanceController systemUnderTest;

	@Test
	void testGetFinancesSuccess() {
		// Given
		when(financeService.getAll(USER_ID)).thenReturn(createFinanceDtoList());

		// When
		final ResponseEntity<List<FinanceDto>> response = systemUnderTest.getFinances(PRINCIPAL);

		// Then
		verify(financeService).getAll(USER_ID);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();

	}

	@Test
	void testGetFinancesSuccessWhenListEmpty() {
		// Given
		when(financeService.getAll(USER_ID)).thenReturn(null);

		// When
		final ResponseEntity<List<FinanceDto>> response = systemUnderTest.getFinances(PRINCIPAL);

		// Then
		verify(financeService).getAll(USER_ID);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNull();
	}

	@Test
	void testUpdateFinanceSuccess() {
		// Given
		final FinanceDto financeDto = new FinanceDto(ID, NAME, FinanceType.EXPENSE, AMOUNT, LOCAL_DATE);
		when(financeService.update(financeDto, USER_ID)).thenReturn(financeDto);

		// When
		final ResponseEntity<FinanceDto> result = systemUnderTest.updateFinance(financeDto, PRINCIPAL);

		// Then
		verify(financeService).update(financeDto, PRINCIPAL.getName());
		assertThat(result.getBody()).isEqualTo(financeDto);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testCreateFinanceSuccess() {
		// Given
		final FinanceDto financeDto = new FinanceDto(ID, NAME, FinanceType.EXPENSE, AMOUNT, LOCAL_DATE);
		when(financeService.create(financeDto, USER_ID)).thenReturn(financeDto);

		// When
		final ResponseEntity<FinanceDto> result = systemUnderTest.createFinance(financeDto, PRINCIPAL);

		// Then
		verify(financeService).create(financeDto, PRINCIPAL.getName());
		assertThat(result.getBody()).isEqualTo(financeDto);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	private List<FinanceDto> createFinanceDtoList() {
		return List.of(
				new FinanceDto(ID, NAME, FinanceType.EXPENSE, AMOUNT, LOCAL_DATE),
				new FinanceDto("2", "Transportation", FinanceType.EXPENSE, BigDecimal.valueOf(30.5), LOCAL_DATE),
				new FinanceDto("3", "Insurance", FinanceType.EXPENSE, BigDecimal.valueOf(75.75), LOCAL_DATE),
				new FinanceDto("4", "Salary", FinanceType.INCOME, BigDecimal.valueOf(100), LOCAL_DATE));
	}
}