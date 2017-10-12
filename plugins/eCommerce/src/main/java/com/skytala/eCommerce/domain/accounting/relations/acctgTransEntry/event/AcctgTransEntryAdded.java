package com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.model.AcctgTransEntry;
public class AcctgTransEntryAdded implements Event{

	private AcctgTransEntry addedAcctgTransEntry;
	private boolean success;

	public AcctgTransEntryAdded(AcctgTransEntry addedAcctgTransEntry, boolean success){
		this.addedAcctgTransEntry = addedAcctgTransEntry;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AcctgTransEntry getAddedAcctgTransEntry() {
		return addedAcctgTransEntry;
	}

}
