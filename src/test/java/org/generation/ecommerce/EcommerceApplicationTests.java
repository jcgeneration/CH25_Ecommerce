package org.generation.ecommerce;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.generation.ecommerce.model.Producto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;


@SpringBootTest
@AutoConfigureMockMvc
class EcommerceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	// TODO actualizar el token con uno válido
	private final String token="Bearer: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbXlzQGdtYWlsLmNvbSIsInJvbGUiOiJ1c2VyIiwiaWF0IjoxNjg0Mjc1MjEzLCJleHAiOjE2ODQzMTEyMTN9.XM49M6hNkC4wm6Dw0t17pVG9-XXkSXbJddyH2Fs3tVA";
	
	@Test
	@DisplayName("Prueba para obtener (GET) la lista de productos")
	void pruebaGET() throws Exception  {
		this.mockMvc.perform(get("/api/productos/1"))
			.andDo(print())
			.andExpect(status().isOk() )
			.andExpect(
					content().string(
						containsString("norma1.gif")
							)
					);
	}//pruebaGET

	@Test
	@Disabled("Probado la primera vez, deshabilitado.")
	@DisplayName("Prueba para borrar el producto con id 7, esta prueba sólo se puede hacer una vez")
	void pruebaDELETE() throws Exception {
		this.mockMvc.perform(delete("/api/productos/7")
				.header("Authorization", token)
				)
		.andDo(print())
		.andExpect(status().isOk() )
		.andExpect(
				content().string(
					containsString("bic2.jpg")
						)
				);
	}//pruebaDELETE
	
	@Test
	@Disabled("Se agregó correctamente el producto")
	@DisplayName("Prueba para crear un nuevo producto")
	void pruebaPOST() throws Exception {
		Producto p = new Producto();
		p.setNombre("Cuaderno Profesional Scribe Clásico / Doble Raya / 200 hojas");
		p.setDescripcion("Cuaderno Profesional Scribe Clásico / Doble Raya / 200 hojas");
		p.setImagen("producto_9.jpg");
		p.setPrecio(120.01);
		this.mockMvc.perform(post("/api/productos/")
				.contentType(MediaType.APPLICATION_JSON)
				.content( asJsonString(p))
				.header("Authorization", token)
				)
		.andDo(print())
		.andExpect(status().isOk() )
		.andExpect(
				content().string(
					containsString("producto_9.jpg")
						)
				);
	}//pruebaPOST

	private static String asJsonString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }//catch
	 } // asJsonString
	
	
	@Test
	@DisplayName("Se modifica el producto 5 con el nuevo precio")
	void pruebaPUT() throws Exception  {
		
		this.mockMvc.perform(put("/api/productos/5")
				.queryParam("precio", "27.08")
				.header("Authorization", token)
				)
		.andDo(print())
		.andExpect(status().isOk() )
		.andExpect(
				content().string(
					containsString("27.08")
						)
				);
		
	}//pruebaPUT
	
	
	
}//class EcommerceApplicationTests