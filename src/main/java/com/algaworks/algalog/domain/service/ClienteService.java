package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.exception.RuleException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {
	private ClienteRepository clienteRepository;
	
	public Cliente save(Cliente clienteData) {
		boolean emailEmUso = clienteRepository.findByEmail(clienteData.getEmail())
				.stream()
				.anyMatch(cliente -> !cliente.equals(clienteData));
		
		if (emailEmUso) {
			throw new RuleException("O e-mail já cadastrado.");
		}
		
		return clienteRepository.save(clienteData);
	}
	
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}
}
