package com.skytala.eCommerce.domain.product.relations.productMeter.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productMeter.model.ProductMeter;
public class ProductMeterFound implements Event{

	private List<ProductMeter> productMeters;

	public ProductMeterFound(List<ProductMeter> productMeters) {
		this.productMeters = productMeters;
	}

	public List<ProductMeter> getProductMeters()	{
		return productMeters;
	}

}
