package com.skytala.eCommerce.domain.productFeatureType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productFeatureType.model.ProductFeatureType;
public class ProductFeatureTypeFound implements Event{

	private List<ProductFeatureType> productFeatureTypes;

	public ProductFeatureTypeFound(List<ProductFeatureType> productFeatureTypes) {
		this.productFeatureTypes = productFeatureTypes;
	}

	public List<ProductFeatureType> getProductFeatureTypes()	{
		return productFeatureTypes;
	}

}
