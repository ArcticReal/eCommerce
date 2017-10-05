package com.skytala.eCommerce.domain.mrpEventType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.mrpEventType.model.MrpEventType;
public class MrpEventTypeDeleted implements Event{

	private boolean success;

	public MrpEventTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
