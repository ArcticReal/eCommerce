package com.skytala.eCommerce.domain.product.relations.product.event.featureType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureType.ProductFeatureType;
public class ProductFeatureTypeFound implements Event{

	private List<ProductFeatureType> productFeatureTypes;

	public ProductFeatureTypeFound(List<ProductFeatureType> productFeatureTypes) {
		this.productFeatureTypes = productFeatureTypes;
	}

	public List<ProductFeatureType> getProductFeatureTypes()	{
		return productFeatureTypes;
	}

}
