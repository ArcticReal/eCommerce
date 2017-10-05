package com.skytala.eCommerce.domain.acctgTrans.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.acctgTrans.model.AcctgTrans;
public class AcctgTransUpdated implements Event{

	private boolean success;

	public AcctgTransUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
