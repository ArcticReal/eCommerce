package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.responsibility;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.responsibility.EmplPositionResponsibility;
public class EmplPositionResponsibilityFound implements Event{

	private List<EmplPositionResponsibility> emplPositionResponsibilitys;

	public EmplPositionResponsibilityFound(List<EmplPositionResponsibility> emplPositionResponsibilitys) {
		this.emplPositionResponsibilitys = emplPositionResponsibilitys;
	}

	public List<EmplPositionResponsibility> getEmplPositionResponsibilitys()	{
		return emplPositionResponsibilitys;
	}

}
