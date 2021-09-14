package br.com.vivo.productms.service;

import java.util.List;

import br.com.vivo.productms.dto.ProductDto;
import br.com.vivo.productms.dto.ProductRequestDto;

public interface ProductServiceInterface {

	public List<ProductDto> findAll();
	public ProductDto findById(String id);
	public ProductDto create(ProductRequestDto product);
	public ProductDto update(ProductRequestDto product, String id);
	public Boolean deleteById(String id);
	public List<ProductDto> search(String q, Double minPrice, Double maxPrice);
}
