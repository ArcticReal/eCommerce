package com.skytala.eCommerce.domain.product.relations.product.event.featurePrice;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featurePrice.ProductFeaturePrice;
public class ProductFeaturePriceAdded implements Event{

	private ProductFeaturePrice addedProductFeaturePrice;
	private boolean success;

	public ProductFeaturePriceAdded(ProductFeaturePrice addedProductFeaturePrice, boolean success){
		this.addedProductFeaturePrice = addedProductFeaturePrice;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeaturePrice getAddedProductFeaturePrice() {
		return addedProductFeaturePrice;
	}

}
