package com.skytala.eCommerce.query;

public class FindProductPricesBy implements Query {

	private String productId;

	public FindProductPricesBy(String productId) {
		this.setProductId(productId);
	}

	@Override
	public void execute() {
		//TODO: find prices
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
