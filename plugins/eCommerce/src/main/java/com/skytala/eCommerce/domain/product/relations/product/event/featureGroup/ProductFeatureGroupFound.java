package com.skytala.eCommerce.domain.product.relations.product.event.featureGroup;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureGroup.ProductFeatureGroup;
public class ProductFeatureGroupFound implements Event{

	private List<ProductFeatureGroup> productFeatureGroups;

	public ProductFeatureGroupFound(List<ProductFeatureGroup> productFeatureGroups) {
		this.productFeatureGroups = productFeatureGroups;
	}

	public List<ProductFeatureGroup> getProductFeatureGroups()	{
		return productFeatureGroups;
	}

}
