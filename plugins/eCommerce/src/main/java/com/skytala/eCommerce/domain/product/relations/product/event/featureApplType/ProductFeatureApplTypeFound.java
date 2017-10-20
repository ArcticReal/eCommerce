package com.skytala.eCommerce.domain.product.relations.product.event.featureApplType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureApplType.ProductFeatureApplType;
public class ProductFeatureApplTypeFound implements Event{

	private List<ProductFeatureApplType> productFeatureApplTypes;

	public ProductFeatureApplTypeFound(List<ProductFeatureApplType> productFeatureApplTypes) {
		this.productFeatureApplTypes = productFeatureApplTypes;
	}

	public List<ProductFeatureApplType> getProductFeatureApplTypes()	{
		return productFeatureApplTypes;
	}

}
