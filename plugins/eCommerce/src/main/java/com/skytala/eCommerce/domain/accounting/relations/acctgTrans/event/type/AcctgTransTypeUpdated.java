package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.type.AcctgTransType;
public class AcctgTransTypeUpdated implements Event{

	private boolean success;

	public AcctgTransTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
