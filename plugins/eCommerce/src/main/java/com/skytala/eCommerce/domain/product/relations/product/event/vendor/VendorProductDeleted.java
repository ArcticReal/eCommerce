package com.skytala.eCommerce.domain.product.relations.product.event.vendor;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.vendor.VendorProduct;
public class VendorProductDeleted implements Event{

	private boolean success;

	public VendorProductDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
