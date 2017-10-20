package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.typeAttr.InventoryItemTypeAttr;
public class InventoryItemTypeAttrFound implements Event{

	private List<InventoryItemTypeAttr> inventoryItemTypeAttrs;

	public InventoryItemTypeAttrFound(List<InventoryItemTypeAttr> inventoryItemTypeAttrs) {
		this.inventoryItemTypeAttrs = inventoryItemTypeAttrs;
	}

	public List<InventoryItemTypeAttr> getInventoryItemTypeAttrs()	{
		return inventoryItemTypeAttrs;
	}

}
