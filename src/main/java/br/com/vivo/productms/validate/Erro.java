package br.com.vivo.productms.validate;

//import br.com.vivo.productms.entities.Product;

public class Erro{
	private Integer status_code;
	private String message;
	public Integer getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Erro(Integer status_code, String message) {
		this.status_code = status_code;
		this.message = message;
	}
	public Erro() {

	}
	@Override
	public String toString() {
		return "status_code=" + status_code + ", message=" + message;
	}
	
	
}
