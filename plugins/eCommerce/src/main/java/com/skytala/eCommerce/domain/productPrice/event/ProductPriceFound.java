package com.skytala.eCommerce.domain.productPrice.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPrice.model.ProductPrice;
public class ProductPriceFound implements Event{

	private List<ProductPrice> productPrices;

	public ProductPriceFound(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

	public List<ProductPrice> getProductPrices()	{
		return productPrices;
	}

}
