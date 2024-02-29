package it.sosinski.finances.exception.model;

public class FinanceNotFoundException extends RuntimeException {

	public FinanceNotFoundException(final String id) {
		super("Finance not found with id: " + id);
	}
}
