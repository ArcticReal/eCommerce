package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.AcctgTrans;
public class AcctgTransDeleted implements Event{

	private boolean success;

	public AcctgTransDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
