package com.algaworks.algalog.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorField {
	private String nome;
	private String mensagem;
}
