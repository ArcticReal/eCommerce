package com.skytala.eCommerce.domain.acctgTransType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.acctgTransType.model.AcctgTransType;
public class AcctgTransTypeUpdated implements Event{

	private boolean success;

	public AcctgTransTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
