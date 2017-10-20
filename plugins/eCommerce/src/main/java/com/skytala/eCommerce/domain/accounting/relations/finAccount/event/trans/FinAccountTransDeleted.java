package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.trans;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.trans.FinAccountTrans;
public class FinAccountTransDeleted implements Event{

	private boolean success;

	public FinAccountTransDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
