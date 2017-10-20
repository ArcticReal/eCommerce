package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.type.MrpEventType;
public class MrpEventTypeFound implements Event{

	private List<MrpEventType> mrpEventTypes;

	public MrpEventTypeFound(List<MrpEventType> mrpEventTypes) {
		this.mrpEventTypes = mrpEventTypes;
	}

	public List<MrpEventType> getMrpEventTypes()	{
		return mrpEventTypes;
	}

}
