package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.type.AcctgTransType;
public class AcctgTransTypeDeleted implements Event{

	private boolean success;

	public AcctgTransTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
