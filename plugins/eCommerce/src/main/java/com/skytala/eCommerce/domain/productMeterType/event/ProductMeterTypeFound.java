package com.skytala.eCommerce.domain.productMeterType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productMeterType.model.ProductMeterType;
public class ProductMeterTypeFound implements Event{

	private List<ProductMeterType> productMeterTypes;

	public ProductMeterTypeFound(List<ProductMeterType> productMeterTypes) {
		this.productMeterTypes = productMeterTypes;
	}

	public List<ProductMeterType> getProductMeterTypes()	{
		return productMeterTypes;
	}

}
