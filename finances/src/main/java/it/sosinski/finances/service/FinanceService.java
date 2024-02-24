package it.sosinski.finances.service;

import java.util.List;

import it.sosinski.finances.model.FinanceDto;

public interface FinanceService {

	List<FinanceDto> getFinancesList();

}
