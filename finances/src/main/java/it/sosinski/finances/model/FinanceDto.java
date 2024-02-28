package it.sosinski.finances.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FinanceDto(String id, String name, FinanceType type, BigDecimal amount, LocalDate date) {

}
