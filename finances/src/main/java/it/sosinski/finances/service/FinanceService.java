package it.sosinski.finances.service;

import java.util.List;

import it.sosinski.finances.model.FinanceDto;

public interface FinanceService {

	List<FinanceDto> getAll(final String userId);

	FinanceDto update(final FinanceDto financeDto, final String userId);
}
