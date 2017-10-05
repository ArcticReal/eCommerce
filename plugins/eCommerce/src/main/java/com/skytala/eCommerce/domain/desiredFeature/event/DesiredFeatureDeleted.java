package com.skytala.eCommerce.domain.desiredFeature.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.desiredFeature.model.DesiredFeature;
public class DesiredFeatureDeleted implements Event{

	private boolean success;

	public DesiredFeatureDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
