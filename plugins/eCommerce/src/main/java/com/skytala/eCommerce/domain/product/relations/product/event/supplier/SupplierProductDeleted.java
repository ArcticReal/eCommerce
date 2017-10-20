package com.skytala.eCommerce.domain.product.relations.product.event.supplier;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.supplier.SupplierProduct;
public class SupplierProductDeleted implements Event{

	private boolean success;

	public SupplierProductDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
