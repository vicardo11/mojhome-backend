package it.sosinski.finances.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import it.sosinski.finances.model.FinanceDto;
import it.sosinski.finances.model.FinanceType;
import it.sosinski.finances.repository.entity.FinanceEntity;

class FinanceMapperTest {

	private static final String ID = "1";

	private static final String NAME = "Groceries";

	private static final BigDecimal AMOUNT = BigDecimal.valueOf(50.25);

	private static final LocalDate LOCAL_DATE = LocalDate.parse("2021-01-01");

	private static final String USER_ID = "userId";

	private final FinanceMapper financeMapper = FinanceMapper.INSTANCE;

	@Test
	void shouldMapToFinanceDto() {
		// Given
		final FinanceEntity financeEntity = new FinanceEntity(ID, USER_ID, NAME, FinanceType.EXPENSE, AMOUNT,
				LOCAL_DATE);

		// When
		final FinanceDto financeDto = financeMapper.toFinanceDto(financeEntity);
		// Then
		assertThat(financeDto)
				.extracting(
						FinanceDto::id,
						FinanceDto::name,
						FinanceDto::type,
						FinanceDto::amount,
						FinanceDto::date)
				.containsExactly(ID,
						NAME,
						FinanceType.EXPENSE,
						AMOUNT,
						LOCAL_DATE);
	}

}