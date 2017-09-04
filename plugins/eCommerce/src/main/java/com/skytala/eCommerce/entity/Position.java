package com.skytala.eCommerce.entity;

import java.math.BigDecimal;

public class Position {

	Product product = new Product();
	BigDecimal productNumber = new BigDecimal(0);

	public Position(Product newProduct, BigDecimal numberProducts) {
		this.product = newProduct;
		this.productNumber = numberProducts;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getNumberProducts() {
		return productNumber;
	}

	public void setNumberProducts(BigDecimal numberProducts) {
		this.productNumber = numberProducts;
	}

}
