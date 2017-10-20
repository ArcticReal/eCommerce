package com.skytala.eCommerce.domain.shipment.relations.picklist.event.statusHistory;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklist.model.statusHistory.PicklistStatusHistory;
public class PicklistStatusHistoryUpdated implements Event{

	private boolean success;

	public PicklistStatusHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
