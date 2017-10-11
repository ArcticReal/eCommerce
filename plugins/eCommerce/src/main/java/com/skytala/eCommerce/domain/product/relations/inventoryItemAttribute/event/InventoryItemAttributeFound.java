package com.skytala.eCommerce.domain.product.relations.inventoryItemAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemAttribute.model.InventoryItemAttribute;
public class InventoryItemAttributeFound implements Event{

	private List<InventoryItemAttribute> inventoryItemAttributes;

	public InventoryItemAttributeFound(List<InventoryItemAttribute> inventoryItemAttributes) {
		this.inventoryItemAttributes = inventoryItemAttributes;
	}

	public List<InventoryItemAttribute> getInventoryItemAttributes()	{
		return inventoryItemAttributes;
	}

}
