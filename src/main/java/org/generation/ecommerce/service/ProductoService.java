package org.generation.ecommerce.service;
import java.util.List;

import org.generation.ecommerce.model.Producto;
import org.generation.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductoService {
	private final ProductoRepository productoRepository;
	@Autowired
	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository=productoRepository;
	}//constructor
	
	public List<Producto> getAllProductos(){
		return productoRepository.findAll();
	}//getAllProductos

	public Producto getProducto(Long id) {
		return productoRepository.findById(id).orElseThrow(
			()-> new IllegalArgumentException("El Producto con id "+ 
								id + " no existe")
				);
	}// getProducto

	public Producto deleteProducto(Long id) {
		Producto tmpProd = null; 
		if (productoRepository.existsById(id)) {
			tmpProd = productoRepository.findById(id).get();
			productoRepository.deleteById(id);
		}//if 
		 return tmpProd;
	}//deleteProducto

public Producto addProducto(Producto producto) {
	Producto tmpProd=null;
	if (productoRepository.findByNombre(producto.getNombre()).isEmpty()) {
		tmpProd = productoRepository.save(producto);
	}else {
		System.out.println("Ya existe un producto con el nombre ["
				+ producto.getNombre() + "]");
	}//if	
	return tmpProd;
}//addProducto

	public Producto updateProducto(Long id, String nombre, 
			String descripcion, String imagen, Double precio) {
		Producto tmpProd = null; 
		if (productoRepository.existsById(id)) {
			tmpProd = productoRepository.findById(id).get();
			if (nombre!=null) tmpProd.setNombre(nombre);
			if (descripcion!=null) tmpProd.setDescripcion(descripcion);
			if (imagen!=null) tmpProd.setImagen(imagen);
			if (precio!=null) tmpProd.setPrecio(precio.doubleValue());
			productoRepository.save(tmpProd);
		}else {
			System.out.println("Update - El Producto con id "
					+ id + " no existe");
		}//else 
		 return tmpProd;
	}//updateProducto
	
	
}// class ProductoService
