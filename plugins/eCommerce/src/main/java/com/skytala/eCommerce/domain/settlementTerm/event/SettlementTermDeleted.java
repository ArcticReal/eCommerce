package com.skytala.eCommerce.domain.settlementTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.settlementTerm.model.SettlementTerm;
public class SettlementTermDeleted implements Event{

	private boolean success;

	public SettlementTermDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
