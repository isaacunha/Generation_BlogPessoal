package com.generation.blogpessoal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	
	private UsuarioRepository usuarioRepository;
	

	public Optional<Usuario> cadastrarUsuario (Usuario usuario) {
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();
			
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			
			return Optional.of(usuarioRepository.save(usuario));
			
	}
	
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);

	}


}
