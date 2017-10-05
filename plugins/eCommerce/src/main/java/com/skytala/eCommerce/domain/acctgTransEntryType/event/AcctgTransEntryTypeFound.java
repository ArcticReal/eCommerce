package com.skytala.eCommerce.domain.acctgTransEntryType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.acctgTransEntryType.model.AcctgTransEntryType;
public class AcctgTransEntryTypeFound implements Event{

	private List<AcctgTransEntryType> acctgTransEntryTypes;

	public AcctgTransEntryTypeFound(List<AcctgTransEntryType> acctgTransEntryTypes) {
		this.acctgTransEntryTypes = acctgTransEntryTypes;
	}

	public List<AcctgTransEntryType> getAcctgTransEntryTypes()	{
		return acctgTransEntryTypes;
	}

}
