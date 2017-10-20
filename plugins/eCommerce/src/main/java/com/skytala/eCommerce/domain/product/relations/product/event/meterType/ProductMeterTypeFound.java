package com.skytala.eCommerce.domain.product.relations.product.event.meterType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.meterType.ProductMeterType;
public class ProductMeterTypeFound implements Event{

	private List<ProductMeterType> productMeterTypes;

	public ProductMeterTypeFound(List<ProductMeterType> productMeterTypes) {
		this.productMeterTypes = productMeterTypes;
	}

	public List<ProductMeterType> getProductMeterTypes()	{
		return productMeterTypes;
	}

}
