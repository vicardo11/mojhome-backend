package it.sosinski.finances.model;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FinanceType {

	EXPENSE("Expense"),
	INCOME("Income");

	@JsonValue
	private final String name;
}
