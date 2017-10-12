package com.skytala.eCommerce.domain.accounting.relations.finAccountTrans.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTrans.model.FinAccountTrans;
public class FinAccountTransUpdated implements Event{

	private boolean success;

	public FinAccountTransUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
