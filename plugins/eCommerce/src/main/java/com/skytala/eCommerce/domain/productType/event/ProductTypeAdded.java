package com.skytala.eCommerce.domain.productType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productType.model.ProductType;
public class ProductTypeAdded implements Event{

	private ProductType addedProductType;
	private boolean success;

	public ProductTypeAdded(ProductType addedProductType, boolean success){
		this.addedProductType = addedProductType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductType getAddedProductType() {
		return addedProductType;
	}

}
