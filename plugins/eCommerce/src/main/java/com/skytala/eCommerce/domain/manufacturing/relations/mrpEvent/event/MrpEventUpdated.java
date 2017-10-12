package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.MrpEvent;
public class MrpEventUpdated implements Event{

	private boolean success;

	public MrpEventUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
