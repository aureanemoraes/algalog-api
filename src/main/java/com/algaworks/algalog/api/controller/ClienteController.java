package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ClienteController {
	
//	@Autowired
//	private ClienteRepository clienteRepository;
	
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes/search")
	public List<Cliente> search(@RequestParam(name="nome") String pattern) {
		return clienteRepository.findByNomeContaining(pattern);
//		return null;
	}
	
	@GetMapping("/clientes")
	public List<Cliente> lista()
	{
		return clienteRepository.findAll();
	}
	
	
}
