package br.com.vivo.productms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.vivo.productms.entities.Product;

public class ProductRequestDto {
	
	@NotBlank(message="Nome obrigatorio")
	private String name;
	@NotBlank(message="Descrição obrigatoria")
	private String description;
	@Positive(message="O preço deve ter valor positivo")
	private Double price;
	
	public ProductRequestDto(Product product) {
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}
		
	public ProductRequestDto() {

	}

	public ProductRequestDto(@NotBlank(message = "Nome obrigatorio") String name,
			@NotBlank(message = "Descrição obrigatoria") String description,
			@Positive(message = "O preço deve ter valor positivo") Double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Double getPrice() {
		return price;
	}

}
