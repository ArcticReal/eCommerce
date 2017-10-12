package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.ItemIssuance;
public class ItemIssuanceFound implements Event{

	private List<ItemIssuance> itemIssuances;

	public ItemIssuanceFound(List<ItemIssuance> itemIssuances) {
		this.itemIssuances = itemIssuances;
	}

	public List<ItemIssuance> getItemIssuances()	{
		return itemIssuances;
	}

}
