package com.skytala.eCommerce.domain.settlementTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.settlementTerm.model.SettlementTerm;
public class SettlementTermAdded implements Event{

	private SettlementTerm addedSettlementTerm;
	private boolean success;

	public SettlementTermAdded(SettlementTerm addedSettlementTerm, boolean success){
		this.addedSettlementTerm = addedSettlementTerm;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SettlementTerm getAddedSettlementTerm() {
		return addedSettlementTerm;
	}

}
