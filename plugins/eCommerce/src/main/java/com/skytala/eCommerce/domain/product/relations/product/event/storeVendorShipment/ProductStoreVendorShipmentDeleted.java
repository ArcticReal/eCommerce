package com.skytala.eCommerce.domain.product.relations.product.event.storeVendorShipment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeVendorShipment.ProductStoreVendorShipment;
public class ProductStoreVendorShipmentDeleted implements Event{

	private boolean success;

	public ProductStoreVendorShipmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
