package com.skytala.eCommerce.domain.finAccountTrans.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.finAccountTrans.model.FinAccountTrans;
public class FinAccountTransDeleted implements Event{

	private boolean success;

	public FinAccountTransDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
