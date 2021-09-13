package br.com.vivo.productms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vivo.productms.entities.Product;
import br.com.vivo.productms.repository.ProductsRepository;
import br.com.vivo.productms.validate.Erro;
import br.com.vivo.productms.validate.ProductValidate;

@Service
public class ProductService extends ProductValidate {

	@Autowired
	private ProductsRepository repository;

	public ResponseEntity<List<Product>> findAll() {
		List<Product> lista = repository.findAll();
		return ResponseEntity.ok(lista);
	}

	public ResponseEntity<Product> findById(String id) {
		Product obj = repository.findById(id).orElse(null);

		if (obj == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(obj);
	}

	public ResponseEntity<Object> create(Product product) {
		if (validateProduct(product)) {
			Product obj = repository.save(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(obj);
		}
		return ResponseEntity.badRequest().body(new Erro(400, MENSAGEM_BAD_REQUEST_CRIACAO));
	}

	@Transactional
	public ResponseEntity<Object> update(Product product, String id) {
		if (!validateProduct(product)) {
			return ResponseEntity.badRequest().body(new Erro(400, MENSAGEM_BAD_REQUEST_ALTERACAO));
		}
		Product obj = repository.findById(id).orElse(null);

		if (obj == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		obj.setDescription(product.getDescription());
		obj.setName(product.getName());
		obj.setPrice(product.getPrice());
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}

	public ResponseEntity<?> deleteById(String id) {
		Product obj = repository.findById(id).orElse(null);
		if (obj == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<List<Product>> search(String q, Double min_price, Double max_price) {

		List<Product> filtro = new ArrayList<>();

		if (q == null) {
			filtro = repository.findAll();
		} else {

			filtro = repository.findByDescription(q);
			List<Product> filtro2 = repository.findByName(q);
			filtro.addAll(filtro2);
		}

		List<Product> retorno = new ArrayList<>();
		filtro.forEach((product) -> {
			if (min_price != null && max_price != null) {
				if (product.getPrice() >= min_price && product.getPrice() <= max_price) {
					retorno.add(product);
				}
			} else if (min_price != null && max_price == null) {
				if (product.getPrice() >= min_price) {
					retorno.add(product);
				}
			} else if (min_price == null && max_price != null) {
				if (product.getPrice() <= max_price) {
					retorno.add(product);
				}
			}
		});

		return ResponseEntity.ok(retorno);
	}

}
