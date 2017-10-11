package com.skytala.eCommerce.domain.product.relations.physicalInventory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.physicalInventory.model.PhysicalInventory;
public class PhysicalInventoryDeleted implements Event{

	private boolean success;

	public PhysicalInventoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
