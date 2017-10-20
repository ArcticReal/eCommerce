package com.skytala.eCommerce.domain.product.relations.product.event.supplierFeature;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.supplierFeature.SupplierProductFeature;
public class SupplierProductFeatureUpdated implements Event{

	private boolean success;

	public SupplierProductFeatureUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
