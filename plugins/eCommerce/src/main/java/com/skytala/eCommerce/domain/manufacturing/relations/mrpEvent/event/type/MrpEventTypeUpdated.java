package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.type.MrpEventType;
public class MrpEventTypeUpdated implements Event{

	private boolean success;

	public MrpEventTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
