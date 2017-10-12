package com.skytala.eCommerce.domain.accounting.relations.acctgTransType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransType.model.AcctgTransType;
public class AcctgTransTypeUpdated implements Event{

	private boolean success;

	public AcctgTransTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
