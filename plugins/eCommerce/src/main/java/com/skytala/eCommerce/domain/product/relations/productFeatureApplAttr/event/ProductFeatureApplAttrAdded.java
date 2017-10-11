package com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.model.ProductFeatureApplAttr;
public class ProductFeatureApplAttrAdded implements Event{

	private ProductFeatureApplAttr addedProductFeatureApplAttr;
	private boolean success;

	public ProductFeatureApplAttrAdded(ProductFeatureApplAttr addedProductFeatureApplAttr, boolean success){
		this.addedProductFeatureApplAttr = addedProductFeatureApplAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureApplAttr getAddedProductFeatureApplAttr() {
		return addedProductFeatureApplAttr;
	}

}
