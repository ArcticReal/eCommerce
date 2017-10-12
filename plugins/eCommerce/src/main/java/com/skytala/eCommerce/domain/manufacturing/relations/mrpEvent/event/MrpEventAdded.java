package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.MrpEvent;
public class MrpEventAdded implements Event{

	private MrpEvent addedMrpEvent;
	private boolean success;

	public MrpEventAdded(MrpEvent addedMrpEvent, boolean success){
		this.addedMrpEvent = addedMrpEvent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public MrpEvent getAddedMrpEvent() {
		return addedMrpEvent;
	}

}
