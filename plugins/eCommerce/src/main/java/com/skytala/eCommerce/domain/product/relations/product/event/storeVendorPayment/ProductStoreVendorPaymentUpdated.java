package com.skytala.eCommerce.domain.product.relations.product.event.storeVendorPayment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeVendorPayment.ProductStoreVendorPayment;
public class ProductStoreVendorPaymentUpdated implements Event{

	private boolean success;

	public ProductStoreVendorPaymentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
