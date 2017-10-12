package com.skytala.eCommerce.domain.humanres.relations.emplPositionFulfillment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionFulfillment.model.EmplPositionFulfillment;
public class EmplPositionFulfillmentFound implements Event{

	private List<EmplPositionFulfillment> emplPositionFulfillments;

	public EmplPositionFulfillmentFound(List<EmplPositionFulfillment> emplPositionFulfillments) {
		this.emplPositionFulfillments = emplPositionFulfillments;
	}

	public List<EmplPositionFulfillment> getEmplPositionFulfillments()	{
		return emplPositionFulfillments;
	}

}
