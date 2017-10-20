package com.skytala.eCommerce.domain.product.relations.product.event.vendor;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.vendor.VendorProduct;
public class VendorProductUpdated implements Event{

	private boolean success;

	public VendorProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
