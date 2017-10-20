package com.skytala.eCommerce.domain.product.relations.product.event.featureApplAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureApplAttr.ProductFeatureApplAttr;
public class ProductFeatureApplAttrDeleted implements Event{

	private boolean success;

	public ProductFeatureApplAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
