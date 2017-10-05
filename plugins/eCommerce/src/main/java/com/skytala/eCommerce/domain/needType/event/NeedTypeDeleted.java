package com.skytala.eCommerce.domain.needType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.needType.model.NeedType;
public class NeedTypeDeleted implements Event{

	private boolean success;

	public NeedTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
