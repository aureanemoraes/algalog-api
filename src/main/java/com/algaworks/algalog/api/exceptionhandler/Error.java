package com.algaworks.algalog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Error {
	@NonNull
	private Integer status;
	@NonNull
	private LocalDateTime dataHora;
	@NonNull
	private String titulo;
	private List<ErrorField> campos;
}
