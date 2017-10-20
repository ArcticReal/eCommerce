package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.fulfillment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.fulfillment.EmplPositionFulfillment;
public class EmplPositionFulfillmentFound implements Event{

	private List<EmplPositionFulfillment> emplPositionFulfillments;

	public EmplPositionFulfillmentFound(List<EmplPositionFulfillment> emplPositionFulfillments) {
		this.emplPositionFulfillments = emplPositionFulfillments;
	}

	public List<EmplPositionFulfillment> getEmplPositionFulfillments()	{
		return emplPositionFulfillments;
	}

}
