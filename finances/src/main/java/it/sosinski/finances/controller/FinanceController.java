package it.sosinski.finances.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sosinski.finances.model.FinanceDto;
import it.sosinski.finances.service.FinanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/finances")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
@Slf4j
public class FinanceController {

	private final FinanceService financeService;

	@GetMapping
	public ResponseEntity<List<FinanceDto>> getFinances() {
		LOG.info("getFinances():: Entry");
		final List<FinanceDto> financeDtos = financeService.getFinancesList();
		return ResponseEntity.ok(financeDtos);
	}



}
