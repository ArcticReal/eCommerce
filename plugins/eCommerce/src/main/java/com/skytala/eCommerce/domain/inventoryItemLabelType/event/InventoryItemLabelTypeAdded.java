package com.skytala.eCommerce.domain.inventoryItemLabelType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.inventoryItemLabelType.model.InventoryItemLabelType;
public class InventoryItemLabelTypeAdded implements Event{

	private InventoryItemLabelType addedInventoryItemLabelType;
	private boolean success;

	public InventoryItemLabelTypeAdded(InventoryItemLabelType addedInventoryItemLabelType, boolean success){
		this.addedInventoryItemLabelType = addedInventoryItemLabelType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryItemLabelType getAddedInventoryItemLabelType() {
		return addedInventoryItemLabelType;
	}

}
