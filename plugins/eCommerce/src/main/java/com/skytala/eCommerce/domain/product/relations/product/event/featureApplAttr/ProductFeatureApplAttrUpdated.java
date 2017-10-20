package com.skytala.eCommerce.domain.product.relations.product.event.featureApplAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureApplAttr.ProductFeatureApplAttr;
public class ProductFeatureApplAttrUpdated implements Event{

	private boolean success;

	public ProductFeatureApplAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
