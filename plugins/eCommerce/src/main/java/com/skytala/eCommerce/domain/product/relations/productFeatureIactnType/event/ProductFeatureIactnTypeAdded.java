package com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.model.ProductFeatureIactnType;
public class ProductFeatureIactnTypeAdded implements Event{

	private ProductFeatureIactnType addedProductFeatureIactnType;
	private boolean success;

	public ProductFeatureIactnTypeAdded(ProductFeatureIactnType addedProductFeatureIactnType, boolean success){
		this.addedProductFeatureIactnType = addedProductFeatureIactnType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureIactnType getAddedProductFeatureIactnType() {
		return addedProductFeatureIactnType;
	}

}
