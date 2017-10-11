package com.skytala.eCommerce.domain.product.relations.vendorProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.vendorProduct.model.VendorProduct;
public class VendorProductDeleted implements Event{

	private boolean success;

	public VendorProductDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
