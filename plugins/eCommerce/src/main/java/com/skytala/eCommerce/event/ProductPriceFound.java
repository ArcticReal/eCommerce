package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.ProductPrice;

public class ProductPriceFound implements Event {

	private List<ProductPrice> productPrices;

	public ProductPriceFound(List<ProductPrice> productPrices) {
		this.setProductPrices(productPrices);
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}
}
