package com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.model.MrpEventType;
public class MrpEventTypeUpdated implements Event{

	private boolean success;

	public MrpEventTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
