package it.sosinski.finances.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;

@Builder
public record FinanceDto(String id, String name, FinanceType type, BigDecimal amount, LocalDate date, String categoryId, String categoryName) {

}
