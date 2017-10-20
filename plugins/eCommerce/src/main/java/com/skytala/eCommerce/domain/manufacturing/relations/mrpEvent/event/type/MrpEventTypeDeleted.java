package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.type.MrpEventType;
public class MrpEventTypeDeleted implements Event{

	private boolean success;

	public MrpEventTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
