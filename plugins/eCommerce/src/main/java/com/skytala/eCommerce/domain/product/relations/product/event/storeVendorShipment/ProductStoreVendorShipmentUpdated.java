package com.skytala.eCommerce.domain.product.relations.product.event.storeVendorShipment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeVendorShipment.ProductStoreVendorShipment;
public class ProductStoreVendorShipmentUpdated implements Event{

	private boolean success;

	public ProductStoreVendorShipmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
