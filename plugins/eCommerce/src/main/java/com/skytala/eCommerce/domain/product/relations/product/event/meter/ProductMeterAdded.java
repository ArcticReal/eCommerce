package com.skytala.eCommerce.domain.product.relations.product.event.meter;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.meter.ProductMeter;
public class ProductMeterAdded implements Event{

	private ProductMeter addedProductMeter;
	private boolean success;

	public ProductMeterAdded(ProductMeter addedProductMeter, boolean success){
		this.addedProductMeter = addedProductMeter;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductMeter getAddedProductMeter() {
		return addedProductMeter;
	}

}
