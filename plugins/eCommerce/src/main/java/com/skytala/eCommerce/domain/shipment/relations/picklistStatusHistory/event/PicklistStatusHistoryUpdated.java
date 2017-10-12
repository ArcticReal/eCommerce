package com.skytala.eCommerce.domain.shipment.relations.picklistStatusHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklistStatusHistory.model.PicklistStatusHistory;
public class PicklistStatusHistoryUpdated implements Event{

	private boolean success;

	public PicklistStatusHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
