package it.sosinski.finances.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sosinski.aspectlibrary.logger.LogMethodAround;
import it.sosinski.finances.model.FinanceDto;
import it.sosinski.finances.service.FinanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/secured/finances")
@CrossOrigin("https://mojhome.vercel.app")
@RequiredArgsConstructor
@Slf4j
public class FinanceController {

	private final FinanceService financeService;

	@GetMapping
	@LogMethodAround
	public ResponseEntity<List<FinanceDto>> getFinances(final Principal principal) {
		final List<FinanceDto> financeDtos = financeService.getAll(principal.getName());
		return ResponseEntity.ok(financeDtos);
	}

	@PutMapping
	@LogMethodAround
	public ResponseEntity<FinanceDto> updateFinance(@RequestBody final FinanceDto financeDto, final Principal principal) {
		final FinanceDto updatedFinanceDto = financeService.update(financeDto, principal.getName());
		return ResponseEntity.ok(updatedFinanceDto);
	}

	@PostMapping
	@LogMethodAround
	public ResponseEntity<FinanceDto> createFinance(@RequestBody final FinanceDto financeDto, final Principal principal) {
		final FinanceDto createdFinanceDto = financeService.create(financeDto, principal.getName());
		return ResponseEntity.ok(createdFinanceDto);
	}

	@DeleteMapping("/{financeId}")
	@LogMethodAround
	public ResponseEntity<List<FinanceDto>> deleteFinance(@PathVariable final String financeId, final Principal principal) {
		final List<FinanceDto> financeDtos = financeService.delete(financeId, principal.getName());
		return ResponseEntity.ok(financeDtos);
	}

}
