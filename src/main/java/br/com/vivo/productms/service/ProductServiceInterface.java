package br.com.vivo.productms.service;

import java.util.List;

import br.com.vivo.productms.dto.ProductDto;
import br.com.vivo.productms.entities.Product;

public interface ProductServiceInterface {

	public List<ProductDto> findAll();
	public ProductDto findById(String id);
	public ProductDto create(Product product);
	public ProductDto update(Product product, String id);
	public Boolean deleteById(String id);
	public List<ProductDto> search(String q, Double min_price, Double max_price);
}
