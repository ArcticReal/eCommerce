package com.skytala.eCommerce.domain.accounting.relations.acctgTransType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransType.model.AcctgTransType;
public class AcctgTransTypeAdded implements Event{

	private AcctgTransType addedAcctgTransType;
	private boolean success;

	public AcctgTransTypeAdded(AcctgTransType addedAcctgTransType, boolean success){
		this.addedAcctgTransType = addedAcctgTransType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AcctgTransType getAddedAcctgTransType() {
		return addedAcctgTransType;
	}

}
