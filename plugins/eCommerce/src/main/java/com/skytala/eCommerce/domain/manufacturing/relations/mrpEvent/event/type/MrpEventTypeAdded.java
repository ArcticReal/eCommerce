package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.type.MrpEventType;
public class MrpEventTypeAdded implements Event{

	private MrpEventType addedMrpEventType;
	private boolean success;

	public MrpEventTypeAdded(MrpEventType addedMrpEventType, boolean success){
		this.addedMrpEventType = addedMrpEventType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public MrpEventType getAddedMrpEventType() {
		return addedMrpEventType;
	}

}
