package com.skytala.eCommerce.domain.productPriceChange.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPriceChange.model.ProductPriceChange;
public class ProductPriceChangeFound implements Event{

	private List<ProductPriceChange> productPriceChanges;

	public ProductPriceChangeFound(List<ProductPriceChange> productPriceChanges) {
		this.productPriceChanges = productPriceChanges;
	}

	public List<ProductPriceChange> getProductPriceChanges()	{
		return productPriceChanges;
	}

}
