package com.skytala.eCommerce.domain.order.relations.desiredFeature.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.desiredFeature.model.DesiredFeature;
public class DesiredFeatureDeleted implements Event{

	private boolean success;

	public DesiredFeatureDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
