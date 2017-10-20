package com.skytala.eCommerce.domain.product.relations.product.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.type.ProductType;
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
