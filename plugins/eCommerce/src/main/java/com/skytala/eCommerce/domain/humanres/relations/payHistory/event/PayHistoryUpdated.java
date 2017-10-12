package com.skytala.eCommerce.domain.humanres.relations.payHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.payHistory.model.PayHistory;
public class PayHistoryUpdated implements Event{

	private boolean success;

	public PayHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
