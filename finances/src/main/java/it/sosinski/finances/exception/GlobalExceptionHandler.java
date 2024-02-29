package it.sosinski.finances.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.sosinski.finances.exception.model.UserMismatchException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { UserMismatchException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handler(Exception exception) {
		LOG.debug("handler:: exception={}", exception.getMessage());
		return ErrorResponse.builder()
				.message(exception.getMessage())
				.code(HttpStatus.BAD_REQUEST.value())
				.build();
	}

}
