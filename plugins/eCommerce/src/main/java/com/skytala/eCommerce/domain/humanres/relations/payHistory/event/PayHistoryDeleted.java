package com.skytala.eCommerce.domain.humanres.relations.payHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.payHistory.model.PayHistory;
public class PayHistoryDeleted implements Event{

	private boolean success;

	public PayHistoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
