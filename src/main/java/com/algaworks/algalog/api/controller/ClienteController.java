package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> lista()
	{
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Cliente Um");
		cliente1.setEmail("clienteum@email.com");
		cliente1.setTelefone("96 99999-9999");
		
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Cliente Dois");
		cliente2.setEmail("clientedois@email.com");
		cliente2.setTelefone("96 99999-9999");
		
		return Arrays.asList(cliente1, cliente2);
	}
}
