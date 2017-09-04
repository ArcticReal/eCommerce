package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.ProductPrice;

public class ProductPricesFound implements Event {

	private List<ProductPrice> foundProductPrices;

	public ProductPricesFound(List<ProductPrice> foundProductPrices) {
		this.setFoundProductPrices(foundProductPrices);
	}

	public List<ProductPrice> getFoundProductPrices() {
		return foundProductPrices;
	}

	public void setFoundProductPrices(List<ProductPrice> foundProductPrices) {
		this.foundProductPrices = foundProductPrices;
	}

}
