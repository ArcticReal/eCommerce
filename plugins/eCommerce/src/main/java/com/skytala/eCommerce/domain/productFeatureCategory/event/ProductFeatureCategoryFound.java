package com.skytala.eCommerce.domain.productFeatureCategory.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productFeatureCategory.model.ProductFeatureCategory;
public class ProductFeatureCategoryFound implements Event{

	private List<ProductFeatureCategory> productFeatureCategorys;

	public ProductFeatureCategoryFound(List<ProductFeatureCategory> productFeatureCategorys) {
		this.productFeatureCategorys = productFeatureCategorys;
	}

	public List<ProductFeatureCategory> getProductFeatureCategorys()	{
		return productFeatureCategorys;
	}

}
