package com.skytala.eCommerce.domain.product.relations.product.event.featureIactn;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureIactn.ProductFeatureIactn;
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
