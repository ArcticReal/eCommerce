package com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeShipmentMeth.ProductStoreShipmentMeth;
public class ProductStoreShipmentMethUpdated implements Event{

	private boolean success;

	public ProductStoreShipmentMethUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
