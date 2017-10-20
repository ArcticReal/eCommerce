package com.skytala.eCommerce.domain.product.relations.product.event.supplier;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.supplier.SupplierProduct;
public class SupplierProductUpdated implements Event{

	private boolean success;

	public SupplierProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
