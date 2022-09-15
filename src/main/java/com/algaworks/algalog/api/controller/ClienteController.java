package com.algaworks.algalog.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("clientes")
public class ClienteController {
	
//	@Autowired
//	private ClienteRepository clienteRepository;
	
	private ClienteRepository clienteRepository;
	
	@GetMapping()
	public List<Cliente> lista()
	{
		return clienteRepository.findAll();
	}
	
	@GetMapping("/search")
	public List<Cliente> search(@RequestParam(name="nome") String pattern) {
		return clienteRepository.findByNomeContaining(pattern);
//		return null;
	}	
	
	@GetMapping("/{id}")
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
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente store(@Validated @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
//		return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable long id, @Validated @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}	
		
		cliente.setId(id);
		Cliente clienteData = clienteRepository.save(cliente);
		return ResponseEntity.ok(clienteData);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
