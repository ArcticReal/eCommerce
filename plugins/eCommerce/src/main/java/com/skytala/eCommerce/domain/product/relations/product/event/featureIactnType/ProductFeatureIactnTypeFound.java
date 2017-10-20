package com.skytala.eCommerce.domain.product.relations.product.event.featureIactnType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureIactnType.ProductFeatureIactnType;
public class ProductFeatureIactnTypeFound implements Event{

	private List<ProductFeatureIactnType> productFeatureIactnTypes;

	public ProductFeatureIactnTypeFound(List<ProductFeatureIactnType> productFeatureIactnTypes) {
		this.productFeatureIactnTypes = productFeatureIactnTypes;
	}

	public List<ProductFeatureIactnType> getProductFeatureIactnTypes()	{
		return productFeatureIactnTypes;
	}

}
