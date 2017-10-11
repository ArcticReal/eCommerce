package com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.model.ProductStoreVendorPayment;
public class ProductStoreVendorPaymentDeleted implements Event{

	private boolean success;

	public ProductStoreVendorPaymentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
