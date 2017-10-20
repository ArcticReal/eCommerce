package com.skytala.eCommerce.domain.product.relations.product.event.meterType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.meterType.ProductMeterType;
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
