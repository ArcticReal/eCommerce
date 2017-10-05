package com.skytala.eCommerce.domain.productFeature.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productFeature.model.ProductFeature;
public class ProductFeatureFound implements Event{

	private List<ProductFeature> productFeatures;

	public ProductFeatureFound(List<ProductFeature> productFeatures) {
		this.productFeatures = productFeatures;
	}

	public List<ProductFeature> getProductFeatures()	{
		return productFeatures;
	}

}
