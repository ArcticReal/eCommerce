package com.skytala.eCommerce.domain.supplierProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.supplierProduct.model.SupplierProduct;
public class SupplierProductUpdated implements Event{

	private boolean success;

	public SupplierProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
