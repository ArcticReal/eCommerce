package com.skytala.eCommerce.domain.physicalInventory.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.physicalInventory.model.PhysicalInventory;
public class PhysicalInventoryFound implements Event{

	private List<PhysicalInventory> physicalInventorys;

	public PhysicalInventoryFound(List<PhysicalInventory> physicalInventorys) {
		this.physicalInventorys = physicalInventorys;
	}

	public List<PhysicalInventory> getPhysicalInventorys()	{
		return physicalInventorys;
	}

}
