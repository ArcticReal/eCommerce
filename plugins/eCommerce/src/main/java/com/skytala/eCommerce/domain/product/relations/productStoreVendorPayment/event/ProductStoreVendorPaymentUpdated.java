package com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.model.ProductStoreVendorPayment;
public class ProductStoreVendorPaymentUpdated implements Event{

	private boolean success;

	public ProductStoreVendorPaymentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
