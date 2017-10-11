package com.skytala.eCommerce.domain.product.relations.productFeatureIactn.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.model.ProductFeatureIactn;
public class ProductFeatureIactnAdded implements Event{

	private ProductFeatureIactn addedProductFeatureIactn;
	private boolean success;

	public ProductFeatureIactnAdded(ProductFeatureIactn addedProductFeatureIactn, boolean success){
		this.addedProductFeatureIactn = addedProductFeatureIactn;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureIactn getAddedProductFeatureIactn() {
		return addedProductFeatureIactn;
	}

}
