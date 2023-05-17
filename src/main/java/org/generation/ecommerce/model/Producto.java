package org.generation.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// POJO - Plain Old Java Object
@Entity
@Table(name="producto")
public class Producto {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private long id;
	@Column(nullable = false)
	private String nombre;
	private String descripcion;
	private String imagen;
	private double precio;
//	private static long total=0;
	public Producto(String nombre, String descripcion, String imagen, double precio) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.precio = precio;
//		Producto.total++;
//		this.id=Producto.total;
	}//constructor
	public Producto() {
//		total++;
//		this.id=Producto.total;
	} //Constructor default
	public String getNombre() {
		return nombre;
	}//getNombre
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}//setNombre
	public String getDescripcion() {
		return descripcion;
	}//getDescripcion
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}//setDescripcion
	public String getImagen() {
		return imagen;
	}//getImagen
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}//setImagen
	public double getPrecio() {
		return precio;
	}//getPrecio
	public void setPrecio(double precio) {
		this.precio = precio;
	}//setPrecio
	public long getId() {
		return id;
	}//getId
	public void setId(long id) {
		this.id = id;
	}//setId
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen + ", precio="
				+ precio + ", id=" + id + "]";
	}//toString
}//class Producto
