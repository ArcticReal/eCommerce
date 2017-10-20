package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entry;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entry.AcctgTransEntry;
public class AcctgTransEntryDeleted implements Event{

	private boolean success;

	public AcctgTransEntryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
