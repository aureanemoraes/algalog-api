package com.algaworks.algalog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Error {
	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	private List<ErrorField> campos;
}
