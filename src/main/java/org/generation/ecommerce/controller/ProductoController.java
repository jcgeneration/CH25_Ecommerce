package org.generation.ecommerce.controller;
import java.util.List;
import org.generation.ecommerce.model.Producto;
import org.generation.ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// CRUD   Create   -   Read    -   Update   -     Delete
// HTTP    POST    -   *GET     -    PUT     -     *DELETE
@RestController
@RequestMapping (path="/api/productos/")
//@CrossOrigin(origins="http://localhost:5500")
public class ProductoController {
	private final ProductoService productoService;
	@Autowired
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}//constructor
	@GetMapping            // http://127.0.0.1:8080/api/productos/
	public List<Producto> getAllProducts(){
		return productoService.getAllProductos();
	}//getAllProducts
	
	@GetMapping (path="{prodId}") // http://127.0.0.1:8080/api/productos/2
	public Producto getProduct(@PathVariable("prodId") Long id) {
		return productoService.getProducto(id);
	}//getProduct
	
	@DeleteMapping (path="{prodId}") // http://127.0.0.1:8080/api/productos/2
	public Producto deleteProduct(@PathVariable("prodId") Long id) {
		return productoService.deleteProducto(id);
	}//deleteProduct
	
	@PostMapping // http://127.0.0.1:8080/api/productos/
	public Producto addProduct (@RequestBody Producto producto) {
		return productoService.addProducto(producto);
	}//addProduct
	
	@PutMapping  (path="{prodId}") // http://127.0.0.1:8080/api/productos/4
	public Producto updateProduct (@PathVariable("prodId") Long id, 
		@RequestParam(required = false)	String nombre,
		@RequestParam(required = false)	String descripcion,
		@RequestParam(required = false) String imagen,
		@RequestParam(required = false) Double precio) {
		return productoService.updateProducto(id, nombre, descripcion,
				imagen, precio);
	}//updateProduct
	
}//class ProductoController
