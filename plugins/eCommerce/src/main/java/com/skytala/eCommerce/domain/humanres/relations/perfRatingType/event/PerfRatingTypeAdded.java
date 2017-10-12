package com.skytala.eCommerce.domain.humanres.relations.perfRatingType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.model.PerfRatingType;
public class PerfRatingTypeAdded implements Event{

	private PerfRatingType addedPerfRatingType;
	private boolean success;

	public PerfRatingTypeAdded(PerfRatingType addedPerfRatingType, boolean success){
		this.addedPerfRatingType = addedPerfRatingType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PerfRatingType getAddedPerfRatingType() {
		return addedPerfRatingType;
	}

}
