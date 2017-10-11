package com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.model.InventoryItemLabelAppl;
public class InventoryItemLabelApplAdded implements Event{

	private InventoryItemLabelAppl addedInventoryItemLabelAppl;
	private boolean success;

	public InventoryItemLabelApplAdded(InventoryItemLabelAppl addedInventoryItemLabelAppl, boolean success){
		this.addedInventoryItemLabelAppl = addedInventoryItemLabelAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryItemLabelAppl getAddedInventoryItemLabelAppl() {
		return addedInventoryItemLabelAppl;
	}

}
