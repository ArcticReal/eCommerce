package com.skytala.eCommerce.domain.shipment.relations.picklist.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklist.model.Picklist;
public class PicklistFound implements Event{

	private List<Picklist> picklists;

	public PicklistFound(List<Picklist> picklists) {
		this.picklists = picklists;
	}

	public List<Picklist> getPicklists()	{
		return picklists;
	}

}
