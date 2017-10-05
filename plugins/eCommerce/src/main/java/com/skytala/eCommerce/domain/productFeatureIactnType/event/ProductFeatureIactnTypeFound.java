package com.skytala.eCommerce.domain.productFeatureIactnType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productFeatureIactnType.model.ProductFeatureIactnType;
public class ProductFeatureIactnTypeFound implements Event{

	private List<ProductFeatureIactnType> productFeatureIactnTypes;

	public ProductFeatureIactnTypeFound(List<ProductFeatureIactnType> productFeatureIactnTypes) {
		this.productFeatureIactnTypes = productFeatureIactnTypes;
	}

	public List<ProductFeatureIactnType> getProductFeatureIactnTypes()	{
		return productFeatureIactnTypes;
	}

}
