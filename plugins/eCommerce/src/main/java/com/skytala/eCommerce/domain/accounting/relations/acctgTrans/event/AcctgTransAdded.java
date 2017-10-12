package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.AcctgTrans;
public class AcctgTransAdded implements Event{

	private AcctgTrans addedAcctgTrans;
	private boolean success;

	public AcctgTransAdded(AcctgTrans addedAcctgTrans, boolean success){
		this.addedAcctgTrans = addedAcctgTrans;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AcctgTrans getAddedAcctgTrans() {
		return addedAcctgTrans;
	}

}
