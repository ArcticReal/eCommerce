package com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeShipmentMeth.ProductStoreShipmentMeth;
public class ProductStoreShipmentMethDeleted implements Event{

	private boolean success;

	public ProductStoreShipmentMethDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
