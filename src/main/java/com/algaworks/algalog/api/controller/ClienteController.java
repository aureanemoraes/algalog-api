package com.algaworks.algalog.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> show(@PathVariable Long id) {
		return clienteRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
//		return clienteRepository.findById(id)
//				.map(cliente -> ResponseEntity.ok(cliente))
//				.orElse(ResponseEntity.notFound().build());
		
//		Optional<Cliente> clienteData = clienteRepository.findById(id);
//		if (clienteData.isPresent()) {
//			return ResponseEntity.ok(clienteData.get());
//		}
//		
//		return ResponseEntity.notFound().build();
	}
	
	
}
