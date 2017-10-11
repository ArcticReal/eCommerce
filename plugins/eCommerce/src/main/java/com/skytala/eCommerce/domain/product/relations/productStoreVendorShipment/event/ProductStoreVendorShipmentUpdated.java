package com.skytala.eCommerce.domain.product.relations.productStoreVendorShipment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreVendorShipment.model.ProductStoreVendorShipment;
public class ProductStoreVendorShipmentUpdated implements Event{

	private boolean success;

	public ProductStoreVendorShipmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
