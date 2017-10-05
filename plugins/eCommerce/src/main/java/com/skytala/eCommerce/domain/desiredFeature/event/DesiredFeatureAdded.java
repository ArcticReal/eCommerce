package com.skytala.eCommerce.domain.desiredFeature.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.desiredFeature.model.DesiredFeature;
public class DesiredFeatureAdded implements Event{

	private DesiredFeature addedDesiredFeature;
	private boolean success;

	public DesiredFeatureAdded(DesiredFeature addedDesiredFeature, boolean success){
		this.addedDesiredFeature = addedDesiredFeature;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DesiredFeature getAddedDesiredFeature() {
		return addedDesiredFeature;
	}

}
