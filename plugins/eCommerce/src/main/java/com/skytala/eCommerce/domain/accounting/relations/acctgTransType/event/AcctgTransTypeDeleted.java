package com.skytala.eCommerce.domain.accounting.relations.acctgTransType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransType.model.AcctgTransType;
public class AcctgTransTypeDeleted implements Event{

	private boolean success;

	public AcctgTransTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
