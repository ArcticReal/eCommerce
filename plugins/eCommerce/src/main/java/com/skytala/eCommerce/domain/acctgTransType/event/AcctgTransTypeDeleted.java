package com.skytala.eCommerce.domain.acctgTransType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.acctgTransType.model.AcctgTransType;
public class AcctgTransTypeDeleted implements Event{

	private boolean success;

	public AcctgTransTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
