package com.skytala.eCommerce.domain.productPriceActionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPriceActionType.model.ProductPriceActionType;
public class ProductPriceActionTypeAdded implements Event{

	private ProductPriceActionType addedProductPriceActionType;
	private boolean success;

	public ProductPriceActionTypeAdded(ProductPriceActionType addedProductPriceActionType, boolean success){
		this.addedProductPriceActionType = addedProductPriceActionType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPriceActionType getAddedProductPriceActionType() {
		return addedProductPriceActionType;
	}

}
