package br.com.vivo.productms.validate;

import org.springframework.util.StringUtils;

import br.com.vivo.productms.entities.Product;

public class ProductValidate {
	
    public static final String MENSAGEM_BAD_REQUEST_ALTERACAO = "Dados inválidos para efeturar a alteração do produto informada";
    public static final String MENSAGEM_BAD_REQUEST_CRIACAO = "Dados inválidos para efeturar a criação do produto";
    
    public boolean validateProduct(Product produto) {
        return StringUtils.hasLength(produto.getDescription()) && StringUtils.hasLength(produto.getName()) && (produto.getPrice() >= 0);
    }
}
