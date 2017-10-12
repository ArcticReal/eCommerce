package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.model.EmplPositionTypeRate;
public class EmplPositionTypeRateFound implements Event{

	private List<EmplPositionTypeRate> emplPositionTypeRates;

	public EmplPositionTypeRateFound(List<EmplPositionTypeRate> emplPositionTypeRates) {
		this.emplPositionTypeRates = emplPositionTypeRates;
	}

	public List<EmplPositionTypeRate> getEmplPositionTypeRates()	{
		return emplPositionTypeRates;
	}

}
