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

	private final Principal PRINCIPAL = () -> USER_ID;

	@Mock
	private FinanceService financeService;

	@InjectMocks
	private FinanceController systemUnderTest;

	@Test
	void shouldReturnStatusIsOk() {
		// Given
		when(financeService.getFinancesList(USER_ID)).thenReturn(createFinanceDtoList());

		// When
		final ResponseEntity<List<FinanceDto>> response = systemUnderTest.getFinances(PRINCIPAL);

		// Then
		verify(financeService).getFinancesList(USER_ID);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shouldReturnFinanceDtoList() {
		// Given
		when(financeService.getFinancesList(USER_ID)).thenReturn(createFinanceDtoList());

		// When
		final ResponseEntity<List<FinanceDto>> response = systemUnderTest.getFinances(PRINCIPAL);

		// Then
		verify(financeService).getFinancesList(USER_ID);
		assertThat(response.getBody()).isNotNull();
	}

	@Test
	void shouldReturnEmptyListWhenServiceReturnedNull() {
		// Given
		when(financeService.getFinancesList(USER_ID)).thenReturn(null);

		// When
		final ResponseEntity<List<FinanceDto>> response = systemUnderTest.getFinances(PRINCIPAL);

		// Then
		verify(financeService).getFinancesList(USER_ID);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNull();
	}

	private List<FinanceDto> createFinanceDtoList() {
		return List.of(
				new FinanceDto("1", "1", "Groceries", FinanceType.EXPENSE, BigDecimal.valueOf(50.25), LocalDate.parse("2021-01-01")),
				new FinanceDto("2", "1", "Transportation", FinanceType.EXPENSE, BigDecimal.valueOf(30.5), LocalDate.parse("2021-01-01")),
				new FinanceDto("3", "2", "Insurance", FinanceType.EXPENSE, BigDecimal.valueOf(75.75), LocalDate.parse("2021-01-01")),
				new FinanceDto("4", "2", "Salary", FinanceType.INCOME, BigDecimal.valueOf(100), LocalDate.parse("2021-01-01")));
	}
}