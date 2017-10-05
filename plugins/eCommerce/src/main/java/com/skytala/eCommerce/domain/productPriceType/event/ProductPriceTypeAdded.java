package com.skytala.eCommerce.domain.productPriceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPriceType.model.ProductPriceType;
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
