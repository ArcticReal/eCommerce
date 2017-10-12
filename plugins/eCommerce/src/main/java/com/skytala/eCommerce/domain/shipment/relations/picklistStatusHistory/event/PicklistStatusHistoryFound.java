package com.skytala.eCommerce.domain.shipment.relations.picklistStatusHistory.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklistStatusHistory.model.PicklistStatusHistory;
public class PicklistStatusHistoryFound implements Event{

	private List<PicklistStatusHistory> picklistStatusHistorys;

	public PicklistStatusHistoryFound(List<PicklistStatusHistory> picklistStatusHistorys) {
		this.picklistStatusHistorys = picklistStatusHistorys;
	}

	public List<PicklistStatusHistory> getPicklistStatusHistorys()	{
		return picklistStatusHistorys;
	}

}
