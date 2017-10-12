package com.skytala.eCommerce.domain.humanres.relations.terminationType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.terminationType.model.TerminationType;
public class TerminationTypeFound implements Event{

	private List<TerminationType> terminationTypes;

	public TerminationTypeFound(List<TerminationType> terminationTypes) {
		this.terminationTypes = terminationTypes;
	}

	public List<TerminationType> getTerminationTypes()	{
		return terminationTypes;
	}

}
