package br.com.vivo.productms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vivo.productms.dto.ProductDto;
import br.com.vivo.productms.dto.ProductRequestDto;
import br.com.vivo.productms.entities.Product;
import br.com.vivo.productms.repository.ProductsRepository;

@Service
public class ProductServiceImpl implements ProductServiceInterface{

	@Autowired
	private ProductsRepository repository;

	public List<ProductDto> findAll() {
		List<Product> lista = repository.findAll();
		return ProductDto.converter(lista);
	}

	public ProductDto findById(String id) {
		Product obj = repository.findById(id).orElse(null);
		if (obj == null) {
			return null;
		}
		return new ProductDto(obj);
	}

	public ProductDto create(ProductRequestDto product) {
		Product objsave = new Product(product);
		Product obj = repository.save(objsave);
		return new ProductDto(obj);
	}

	@Transactional
	public ProductDto update(ProductRequestDto product, String id) {
		Product obj = repository.findById(id).orElse(null);

		if (obj == null) {
			return null;
		}
		
		obj.setDescription(product.getDescription());
		obj.setName(product.getName());
		obj.setPrice(product.getPrice());
		return new ProductDto(obj);
	}

	public Boolean deleteById(String id) {
		Product obj = repository.findById(id).orElse(null);
		if (obj == null) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}

	public List<ProductDto> search(String q, Double minPrice, Double maxPrice) {
		
		if(q != null) {
			q = q.toUpperCase();
		}

		List<Product> filtro = repository.search(q, minPrice, maxPrice);
		return ProductDto.converter(filtro);
	}

}
