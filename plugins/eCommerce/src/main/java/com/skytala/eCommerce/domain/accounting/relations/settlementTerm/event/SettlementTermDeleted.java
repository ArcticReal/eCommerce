package com.skytala.eCommerce.domain.accounting.relations.settlementTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.settlementTerm.model.SettlementTerm;
public class SettlementTermDeleted implements Event{

	private boolean success;

	public SettlementTermDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
