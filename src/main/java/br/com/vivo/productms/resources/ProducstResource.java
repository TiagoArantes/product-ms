package br.com.vivo.productms.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivo.productms.dto.ProductDto;
import br.com.vivo.productms.entities.Product;
import br.com.vivo.productms.service.ProductServiceInterface;

@RestController
@RequestMapping(value="/products")
public class ProducstResource{
	
	@Autowired
	private ProductServiceInterface productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> findAll() {
		List<ProductDto> lista = productService.findAll();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable String id) {
		ProductDto obj = productService.findById(id);
		if (obj == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	public ResponseEntity<ProductDto> create(@Valid @RequestBody Product product) {
		ProductDto obj = productService.create(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ProductDto> update(@Valid @RequestBody Product product, @PathVariable String id) {
		ProductDto obj = productService.update(product, id);
		if (obj == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id) {
		if(productService.deleteById(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping(value="/search")
	public ResponseEntity<List<ProductDto>> search(@RequestParam(required = false) String q, @RequestParam(required = false) Double min_price, @RequestParam(required = false) Double max_price) {
		List<ProductDto> filtro = productService.search(q, min_price, max_price);
		return ResponseEntity.ok(filtro);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, Object> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        //String status_code = error.getCode();
	        String errorMessage = error.getDefaultMessage();
	        errors.put("status_code", 400);
	        errors.put("message", errorMessage);
	        
	    });
	    return errors;
	}
}
