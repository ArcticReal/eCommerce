package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.EmplPosition;
public class EmplPositionFound implements Event{

	private List<EmplPosition> emplPositions;

	public EmplPositionFound(List<EmplPosition> emplPositions) {
		this.emplPositions = emplPositions;
	}

	public List<EmplPosition> getEmplPositions()	{
		return emplPositions;
	}

}
