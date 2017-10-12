package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.ItemIssuance;
public class ItemIssuanceAdded implements Event{

	private ItemIssuance addedItemIssuance;
	private boolean success;

	public ItemIssuanceAdded(ItemIssuance addedItemIssuance, boolean success){
		this.addedItemIssuance = addedItemIssuance;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ItemIssuance getAddedItemIssuance() {
		return addedItemIssuance;
	}

}
