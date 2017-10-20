package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.detail;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.detail.InventoryItemDetail;
public class InventoryItemDetailFound implements Event{

	private List<InventoryItemDetail> inventoryItemDetails;

	public InventoryItemDetailFound(List<InventoryItemDetail> inventoryItemDetails) {
		this.inventoryItemDetails = inventoryItemDetails;
	}

	public List<InventoryItemDetail> getInventoryItemDetails()	{
		return inventoryItemDetails;
	}

}
