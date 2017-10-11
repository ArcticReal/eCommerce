package com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.model.InventoryItemLabelAppl;
public class InventoryItemLabelApplFound implements Event{

	private List<InventoryItemLabelAppl> inventoryItemLabelAppls;

	public InventoryItemLabelApplFound(List<InventoryItemLabelAppl> inventoryItemLabelAppls) {
		this.inventoryItemLabelAppls = inventoryItemLabelAppls;
	}

	public List<InventoryItemLabelAppl> getInventoryItemLabelAppls()	{
		return inventoryItemLabelAppls;
	}

}
