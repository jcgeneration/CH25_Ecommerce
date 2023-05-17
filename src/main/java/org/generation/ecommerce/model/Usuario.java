package org.generation.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	public Usuario(Long id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}//constructor
	public Usuario() {} //constructor
	public Long getId() {
		return id;
	}//getId
	public void setId(Long id) {
		this.id = id;
	}//setId
	public String getEmail() {
		return email;
	}//getEmail
	public void setEmail(String email) {
		this.email = email;
	}//setEmail
	public String getPassword() {
		return password;
	}//getPassword
	public void setPassword(String password) {
		this.password = password;
	}//setPassword
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", password=" + password + "]";
	}//toString
		
}//class Usuario
