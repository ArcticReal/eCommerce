package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.MrpEvent;
public class MrpEventFound implements Event{

	private List<MrpEvent> mrpEvents;

	public MrpEventFound(List<MrpEvent> mrpEvents) {
		this.mrpEvents = mrpEvents;
	}

	public List<MrpEvent> getMrpEvents()	{
		return mrpEvents;
	}

}
