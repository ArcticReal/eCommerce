package com.skytala.eCommerce.domain.product.relations.supplierProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.supplierProduct.model.SupplierProduct;
public class SupplierProductDeleted implements Event{

	private boolean success;

	public SupplierProductDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
