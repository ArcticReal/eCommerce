package com.skytala.eCommerce.domain.productMeterType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productMeterType.model.ProductMeterType;
public class ProductMeterTypeAdded implements Event{

	private ProductMeterType addedProductMeterType;
	private boolean success;

	public ProductMeterTypeAdded(ProductMeterType addedProductMeterType, boolean success){
		this.addedProductMeterType = addedProductMeterType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductMeterType getAddedProductMeterType() {
		return addedProductMeterType;
	}

}
