package com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.model.MrpEventType;
public class MrpEventTypeDeleted implements Event{

	private boolean success;

	public MrpEventTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
