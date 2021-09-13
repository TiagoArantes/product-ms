package br.com.vivo.productms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.vivo.productms.entities.Product;

@RestControllerAdvice
public interface ProductsRepository extends JpaRepository<Product, String>{
	
	@Query("select u from Product u where (:q is null or UPPER(u.name) like %:q% or UPPER(u.description) like %:q%) and (:min_price is null or u.price >= :min_price) and (:max_price is null or u.price <= :max_price)")
	List<Product> search(@Param("q") String q, @Param("min_price") Double min_price, @Param("max_price") Double max_price);
}
