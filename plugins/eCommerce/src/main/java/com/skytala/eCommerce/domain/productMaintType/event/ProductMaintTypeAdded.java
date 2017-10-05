package com.skytala.eCommerce.domain.productMaintType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productMaintType.model.ProductMaintType;
public class ProductMaintTypeAdded implements Event{

	private ProductMaintType addedProductMaintType;
	private boolean success;

	public ProductMaintTypeAdded(ProductMaintType addedProductMaintType, boolean success){
		this.addedProductMaintType = addedProductMaintType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductMaintType getAddedProductMaintType() {
		return addedProductMaintType;
	}

}
