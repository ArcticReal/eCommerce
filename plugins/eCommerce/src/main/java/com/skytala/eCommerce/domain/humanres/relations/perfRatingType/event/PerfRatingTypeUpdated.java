package com.skytala.eCommerce.domain.humanres.relations.perfRatingType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.model.PerfRatingType;
public class PerfRatingTypeUpdated implements Event{

	private boolean success;

	public PerfRatingTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
