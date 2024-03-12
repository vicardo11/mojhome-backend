package it.sosinski.finances.exception.model;

public class CategoryNotFoundException extends RuntimeException {

	public CategoryNotFoundException(final String id) {
		super("Finance category not found with id: " + id);
	}
}
