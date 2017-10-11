package com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.model.InventoryItemLabel;
public class InventoryItemLabelAdded implements Event{

	private InventoryItemLabel addedInventoryItemLabel;
	private boolean success;

	public InventoryItemLabelAdded(InventoryItemLabel addedInventoryItemLabel, boolean success){
		this.addedInventoryItemLabel = addedInventoryItemLabel;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryItemLabel getAddedInventoryItemLabel() {
		return addedInventoryItemLabel;
	}

}
