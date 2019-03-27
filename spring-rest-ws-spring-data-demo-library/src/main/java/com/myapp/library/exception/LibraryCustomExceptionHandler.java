package com.myapp.library.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class LibraryCustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		LibraryErrorResponse errRespObj = buildErrorResponse(ex, status);

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		errRespObj.setErrors(errors);

		return new ResponseEntity<Object>(errRespObj, headers, status);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(HttpServletResponse response, HttpHeaders headers,
			Exception ex, HttpStatus status) throws IOException {

		LibraryErrorResponse errRespObj = buildErrorResponse(ex, status);

		return new ResponseEntity<Object>(errRespObj, headers, status);

	}

	private LibraryErrorResponse buildErrorResponse(Exception ex, HttpStatus status) {

		LibraryErrorResponse errRespObj = new LibraryErrorResponse();

		errRespObj.setDateTime(LocalDateTime.now());
		errRespObj.setHttpStatus(status);
		/*
		 * Optional attribute for sending detailed message.
		 */
		// String errorMsg = ex.getMessage();
		// map.put("errorMsg", errorMsg);

		return errRespObj;
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		LibraryErrorResponse errRespObj = buildErrorResponse(ex, status);

		errRespObj
				.setErrors(Arrays.asList("errors", "Missing path variable : " + ex.getParameter().getParameterName()));

		return new ResponseEntity<Object>(errRespObj, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		LibraryErrorResponse errRespObj = buildErrorResponse(ex, status);

		return new ResponseEntity<Object>(errRespObj, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LibraryErrorResponse errRespObj = buildErrorResponse(ex, status);

		return new ResponseEntity<Object>(errRespObj, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		LibraryErrorResponse errRespObj = new LibraryErrorResponse();

		errRespObj.setDateTime(LocalDateTime.now());
		errRespObj.setHttpStatus(status);

		errRespObj.setErrors(Arrays.asList(ex.getMessage()));

		return new ResponseEntity<Object>(errRespObj, headers, status);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(Throwable ex, HttpServletResponse response)
			throws IOException {

		LibraryErrorResponse errRespObj = new LibraryErrorResponse();

		errRespObj.setDateTime(LocalDateTime.now());
		errRespObj.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

		errRespObj.setErrors(Arrays.asList(ex.getMessage()));

		return new ResponseEntity<Object>(errRespObj, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
