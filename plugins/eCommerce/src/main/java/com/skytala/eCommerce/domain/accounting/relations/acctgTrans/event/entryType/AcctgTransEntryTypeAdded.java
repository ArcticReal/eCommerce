package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entryType.AcctgTransEntryType;
public class AcctgTransEntryTypeAdded implements Event{

	private AcctgTransEntryType addedAcctgTransEntryType;
	private boolean success;

	public AcctgTransEntryTypeAdded(AcctgTransEntryType addedAcctgTransEntryType, boolean success){
		this.addedAcctgTransEntryType = addedAcctgTransEntryType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AcctgTransEntryType getAddedAcctgTransEntryType() {
		return addedAcctgTransEntryType;
	}

}
