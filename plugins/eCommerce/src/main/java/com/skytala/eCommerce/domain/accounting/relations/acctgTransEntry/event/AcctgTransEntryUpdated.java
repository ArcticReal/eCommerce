package com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.model.AcctgTransEntry;
public class AcctgTransEntryUpdated implements Event{

	private boolean success;

	public AcctgTransEntryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
