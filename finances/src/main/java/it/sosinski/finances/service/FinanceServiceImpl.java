package it.sosinski.finances.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.sosinski.finances.model.FinanceDto;
import it.sosinski.finances.repository.FinanceRepository;
import it.sosinski.finances.repository.entity.FinanceEntity;
import it.sosinski.finances.service.mapper.FinanceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

	private final FinanceRepository financeRepository;

	private final FinanceMapper financeMapper;

	public List<FinanceDto> getFinancesList() {
		LOG.debug("getFinancesList():: Entry");
		final List<FinanceEntity> entities = financeRepository.findAll();
		return entities.stream()
				.map(financeMapper::toFinanceDto)
				.toList();
	}
}
