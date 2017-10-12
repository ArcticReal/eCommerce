package com.skytala.eCommerce.domain.accounting.relations.acctgTransEntryType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntryType.model.AcctgTransEntryType;
public class AcctgTransEntryTypeDeleted implements Event{

	private boolean success;

	public AcctgTransEntryTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
