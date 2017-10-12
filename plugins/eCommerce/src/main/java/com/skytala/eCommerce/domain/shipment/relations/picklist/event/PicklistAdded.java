package com.skytala.eCommerce.domain.shipment.relations.picklist.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklist.model.Picklist;
public class PicklistAdded implements Event{

	private Picklist addedPicklist;
	private boolean success;

	public PicklistAdded(Picklist addedPicklist, boolean success){
		this.addedPicklist = addedPicklist;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Picklist getAddedPicklist() {
		return addedPicklist;
	}

}
