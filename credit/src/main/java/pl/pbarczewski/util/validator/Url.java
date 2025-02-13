package pl.pbarczewski.util.validator;

import lombok.Getter;

@Getter
public enum Url {
	PRODUCT_URL("http://products:9000/products"),
	CUSTOMER_URL("http://customers:9090/customers"),
	CREDIT_URL("http://credits:9001/credits");

	private String url;

	private Url(String url) {
		this.url = url;
	}
}
