package com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.model.InventoryItemTypeAttr;
public class InventoryItemTypeAttrFound implements Event{

	private List<InventoryItemTypeAttr> inventoryItemTypeAttrs;

	public InventoryItemTypeAttrFound(List<InventoryItemTypeAttr> inventoryItemTypeAttrs) {
		this.inventoryItemTypeAttrs = inventoryItemTypeAttrs;
	}

	public List<InventoryItemTypeAttr> getInventoryItemTypeAttrs()	{
		return inventoryItemTypeAttrs;
	}

}
