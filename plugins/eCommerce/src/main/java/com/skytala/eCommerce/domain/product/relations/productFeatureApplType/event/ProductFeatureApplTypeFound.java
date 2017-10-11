package com.skytala.eCommerce.domain.product.relations.productFeatureApplType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureApplType.model.ProductFeatureApplType;
public class ProductFeatureApplTypeFound implements Event{

	private List<ProductFeatureApplType> productFeatureApplTypes;

	public ProductFeatureApplTypeFound(List<ProductFeatureApplType> productFeatureApplTypes) {
		this.productFeatureApplTypes = productFeatureApplTypes;
	}

	public List<ProductFeatureApplType> getProductFeatureApplTypes()	{
		return productFeatureApplTypes;
	}

}
