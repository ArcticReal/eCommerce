package com.skytala.eCommerce.domain.desiredFeature.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.desiredFeature.model.DesiredFeature;
public class DesiredFeatureFound implements Event{

	private List<DesiredFeature> desiredFeatures;

	public DesiredFeatureFound(List<DesiredFeature> desiredFeatures) {
		this.desiredFeatures = desiredFeatures;
	}

	public List<DesiredFeature> getDesiredFeatures()	{
		return desiredFeatures;
	}

}
