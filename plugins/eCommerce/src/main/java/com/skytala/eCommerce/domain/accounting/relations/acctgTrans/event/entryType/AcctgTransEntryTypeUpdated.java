package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entryType.AcctgTransEntryType;
public class AcctgTransEntryTypeUpdated implements Event{

	private boolean success;

	public AcctgTransEntryTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
