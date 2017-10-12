package com.skytala.eCommerce.domain.humanres.relations.payHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.payHistory.model.PayHistory;
public class PayHistoryAdded implements Event{

	private PayHistory addedPayHistory;
	private boolean success;

	public PayHistoryAdded(PayHistory addedPayHistory, boolean success){
		this.addedPayHistory = addedPayHistory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PayHistory getAddedPayHistory() {
		return addedPayHistory;
	}

}
