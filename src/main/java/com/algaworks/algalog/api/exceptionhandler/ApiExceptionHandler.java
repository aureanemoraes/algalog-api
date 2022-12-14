package com.algaworks.algalog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algalog.domain.exception.RuleException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ErrorField> campos = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ( (FieldError) error ).getField();
//			String mensagem = error.getDefaultMessage();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new ErrorField(nome, mensagem));
		}
		
		Error errors = new Error(
					status.value(),
					LocalDateTime.now(),
					"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente",
					campos
				);
		
		return handleExceptionInternal(ex, errors, headers, status, request);
	}
	
	@ExceptionHandler(RuleException.class)
	public ResponseEntity<Object> handleRuleException(RuleException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Error error = new Error(
					status.value(),
					LocalDateTime.now(),
					ex.getMessage()
				);
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}
}
