package com.skytala.eCommerce.domain.acctgTransType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.acctgTransType.model.AcctgTransType;
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
