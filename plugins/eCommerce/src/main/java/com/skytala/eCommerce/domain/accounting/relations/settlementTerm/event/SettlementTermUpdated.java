package com.skytala.eCommerce.domain.accounting.relations.settlementTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.model.SettlementTerm;
public class SettlementTermUpdated implements Event{

	private boolean success;

	public SettlementTermUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
