package com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.model.InventoryItemDetail;
public class InventoryItemDetailFound implements Event{

	private List<InventoryItemDetail> inventoryItemDetails;

	public InventoryItemDetailFound(List<InventoryItemDetail> inventoryItemDetails) {
		this.inventoryItemDetails = inventoryItemDetails;
	}

	public List<InventoryItemDetail> getInventoryItemDetails()	{
		return inventoryItemDetails;
	}

}
