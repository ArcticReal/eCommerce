package com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.model.AcctgTransEntry;
public class AcctgTransEntryDeleted implements Event{

	private boolean success;

	public AcctgTransEntryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
