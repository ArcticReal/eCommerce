package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.typeAttr.InventoryItemTypeAttr;
public class InventoryItemTypeAttrAdded implements Event{

	private InventoryItemTypeAttr addedInventoryItemTypeAttr;
	private boolean success;

	public InventoryItemTypeAttrAdded(InventoryItemTypeAttr addedInventoryItemTypeAttr, boolean success){
		this.addedInventoryItemTypeAttr = addedInventoryItemTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryItemTypeAttr getAddedInventoryItemTypeAttr() {
		return addedInventoryItemTypeAttr;
	}

}
