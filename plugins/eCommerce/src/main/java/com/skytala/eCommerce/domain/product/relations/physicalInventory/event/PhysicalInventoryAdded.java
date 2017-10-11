package com.skytala.eCommerce.domain.product.relations.physicalInventory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.physicalInventory.model.PhysicalInventory;
public class PhysicalInventoryAdded implements Event{

	private PhysicalInventory addedPhysicalInventory;
	private boolean success;

	public PhysicalInventoryAdded(PhysicalInventory addedPhysicalInventory, boolean success){
		this.addedPhysicalInventory = addedPhysicalInventory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PhysicalInventory getAddedPhysicalInventory() {
		return addedPhysicalInventory;
	}

}
