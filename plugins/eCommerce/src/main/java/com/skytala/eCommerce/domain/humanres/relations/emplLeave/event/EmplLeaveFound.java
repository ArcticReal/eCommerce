package com.skytala.eCommerce.domain.humanres.relations.emplLeave.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.EmplLeave;
public class EmplLeaveFound implements Event{

	private List<EmplLeave> emplLeaves;

	public EmplLeaveFound(List<EmplLeave> emplLeaves) {
		this.emplLeaves = emplLeaves;
	}

	public List<EmplLeave> getEmplLeaves()	{
		return emplLeaves;
	}

}
