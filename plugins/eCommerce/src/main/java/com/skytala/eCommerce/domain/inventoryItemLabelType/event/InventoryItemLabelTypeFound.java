package com.skytala.eCommerce.domain.inventoryItemLabelType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.inventoryItemLabelType.model.InventoryItemLabelType;
public class InventoryItemLabelTypeFound implements Event{

	private List<InventoryItemLabelType> inventoryItemLabelTypes;

	public InventoryItemLabelTypeFound(List<InventoryItemLabelType> inventoryItemLabelTypes) {
		this.inventoryItemLabelTypes = inventoryItemLabelTypes;
	}

	public List<InventoryItemLabelType> getInventoryItemLabelTypes()	{
		return inventoryItemLabelTypes;
	}

}