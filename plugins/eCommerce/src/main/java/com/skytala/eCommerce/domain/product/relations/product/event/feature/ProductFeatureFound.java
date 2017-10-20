package com.skytala.eCommerce.domain.product.relations.product.event.feature;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.feature.ProductFeature;
public class ProductFeatureFound implements Event{

	private List<ProductFeature> productFeatures;

	public ProductFeatureFound(List<ProductFeature> productFeatures) {
		this.productFeatures = productFeatures;
	}

	public List<ProductFeature> getProductFeatures()	{
		return productFeatures;
	}

}
