package com.skytala.eCommerce.domain.mrpEventType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.mrpEventType.model.MrpEventType;
public class MrpEventTypeFound implements Event{

	private List<MrpEventType> mrpEventTypes;

	public MrpEventTypeFound(List<MrpEventType> mrpEventTypes) {
		this.mrpEventTypes = mrpEventTypes;
	}

	public List<MrpEventType> getMrpEventTypes()	{
		return mrpEventTypes;
	}

}