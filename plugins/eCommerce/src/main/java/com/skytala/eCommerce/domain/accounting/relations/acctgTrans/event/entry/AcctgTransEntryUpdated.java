package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entry;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entry.AcctgTransEntry;
public class AcctgTransEntryUpdated implements Event{

	private boolean success;

	public AcctgTransEntryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
