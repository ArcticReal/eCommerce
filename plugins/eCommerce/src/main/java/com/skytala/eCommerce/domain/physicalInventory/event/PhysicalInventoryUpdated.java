package com.skytala.eCommerce.domain.physicalInventory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.physicalInventory.model.PhysicalInventory;
public class PhysicalInventoryUpdated implements Event{

	private boolean success;

	public PhysicalInventoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
