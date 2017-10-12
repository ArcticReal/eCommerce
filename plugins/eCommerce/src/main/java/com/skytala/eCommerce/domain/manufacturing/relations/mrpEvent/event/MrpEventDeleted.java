package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.MrpEvent;
public class MrpEventDeleted implements Event{

	private boolean success;

	public MrpEventDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
