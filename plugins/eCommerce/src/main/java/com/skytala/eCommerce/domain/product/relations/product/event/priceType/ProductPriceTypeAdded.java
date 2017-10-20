package com.skytala.eCommerce.domain.product.relations.product.event.priceType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceType.ProductPriceType;
public class ProductPriceTypeAdded implements Event{

	private ProductPriceType addedProductPriceType;
	private boolean success;

	public ProductPriceTypeAdded(ProductPriceType addedProductPriceType, boolean success){
		this.addedProductPriceType = addedProductPriceType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPriceType getAddedProductPriceType() {
		return addedProductPriceType;
	}

}
