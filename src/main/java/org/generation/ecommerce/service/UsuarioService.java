package org.generation.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.generation.ecommerce.model.ChangePassword;
import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}//constructor


	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}//getAllUsuarios


	public Usuario getUsuario(Long id) {
		return usuarioRepository.findById(id).orElseThrow(
				()-> new IllegalArgumentException("Usuario con id"
						+ id +" no existe.")
				);
	}//getUsuario

	public Usuario deleteUsuario(Long id) {
		Usuario userTmp = null;
		if(usuarioRepository.existsById(id)) {
			userTmp=usuarioRepository.findById(id).get();
			usuarioRepository.deleteById(id);
		}//if
		return userTmp;
	}//deleteUsuario

	public Usuario addUsuario(Usuario usuario) {
		Usuario tmp=null;
		if (usuarioRepository.findByEmail(usuario.getEmail()).isEmpty()) {
			usuario.setPassword(
					passwordEncoder.encode(usuario.getPassword()) // salt 
			);
			tmp = usuarioRepository.save(usuario);
		}//if
		return tmp; 
	}//addUsuario

	public Usuario updateUsuario(Long id, ChangePassword changePassword) {
		Usuario tmp=null;
			if (usuarioRepository.existsById(id)) { // si existe
				if ( (changePassword.getPassword() !=null) &&
					(changePassword.getNewPassword() !=null) ){ // passwords !null
					tmp = usuarioRepository.findById(id).get();
if(passwordEncoder.matches(changePassword.getPassword(), tmp.getPassword())) {
	tmp.setPassword( passwordEncoder.encode(changePassword.getNewPassword()) );
						usuarioRepository.save(tmp);
					} else {
						tmp=null; 
					}//if equals
				}// !null
			}else {
				System.out.println("Update - El usuario con id "
						+ id +" no existe");
			}//else
		return tmp;
	}//updateUsuario

	public boolean validateUsuario(Usuario usuario) {
		Optional<Usuario> userByEmail = 
				usuarioRepository.findByEmail(usuario.getEmail());
		if(userByEmail.isPresent()) {
			Usuario user= userByEmail.get();
		 if (passwordEncoder.matches(usuario.getPassword(), user.getPassword())) {
			//if(user.getPassword().equals(usuario.getPassword())) {
				return true;
			}//if equals
		}//if isPresent
		return false;
	}//validateUsuario
	
	
	
}//class UsuarioService
