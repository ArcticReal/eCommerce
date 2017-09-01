package com.skytala.eCommerce.entity;

public class Position {

	Product product = new Product();
	int productNumber = 0;

	public Position(Product newProduct, int numberProducts) {
		this.product = newProduct;
		this.productNumber = numberProducts;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getNumberProducts() {
		return productNumber;
	}

	public void setNumberProducts(int numberProducts) {
		this.productNumber = numberProducts;
	}

}
