package it.sosinski.finances.exception.model;

public class UserMismatchException extends RuntimeException {

	public UserMismatchException(final String message) {
		super(message);
	}
}
