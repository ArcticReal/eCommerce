package com.skytala.eCommerce.domain.product.relations.product.event.meter;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.meter.ProductMeter;
public class ProductMeterFound implements Event{

	private List<ProductMeter> productMeters;

	public ProductMeterFound(List<ProductMeter> productMeters) {
		this.productMeters = productMeters;
	}

	public List<ProductMeter> getProductMeters()	{
		return productMeters;
	}

}
