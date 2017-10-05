package com.skytala.eCommerce.domain.supplierProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.supplierProduct.model.SupplierProduct;
public class SupplierProductDeleted implements Event{

	private boolean success;

	public SupplierProductDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
