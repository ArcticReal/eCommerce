package com.skytala.eCommerce.domain.shipment.relations.picklistStatusHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklistStatusHistory.model.PicklistStatusHistory;
public class PicklistStatusHistoryAdded implements Event{

	private PicklistStatusHistory addedPicklistStatusHistory;
	private boolean success;

	public PicklistStatusHistoryAdded(PicklistStatusHistory addedPicklistStatusHistory, boolean success){
		this.addedPicklistStatusHistory = addedPicklistStatusHistory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PicklistStatusHistory getAddedPicklistStatusHistory() {
		return addedPicklistStatusHistory;
	}

}
