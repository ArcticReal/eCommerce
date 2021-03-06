package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entry;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entry.AcctgTransEntry;
public class AcctgTransEntryFound implements Event{

	private List<AcctgTransEntry> acctgTransEntrys;

	public AcctgTransEntryFound(List<AcctgTransEntry> acctgTransEntrys) {
		this.acctgTransEntrys = acctgTransEntrys;
	}

	public List<AcctgTransEntry> getAcctgTransEntrys()	{
		return acctgTransEntrys;
	}

}
