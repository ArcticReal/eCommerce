package com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.model.SupplierPrefOrder;
public class SupplierPrefOrderUpdated implements Event{

	private boolean success;

	public SupplierPrefOrderUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
