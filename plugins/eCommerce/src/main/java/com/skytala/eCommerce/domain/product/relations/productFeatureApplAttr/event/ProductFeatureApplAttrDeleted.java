package com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.model.ProductFeatureApplAttr;
public class ProductFeatureApplAttrDeleted implements Event{

	private boolean success;

	public ProductFeatureApplAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
