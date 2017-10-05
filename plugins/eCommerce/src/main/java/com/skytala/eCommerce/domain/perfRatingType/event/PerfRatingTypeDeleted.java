package com.skytala.eCommerce.domain.perfRatingType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.perfRatingType.model.PerfRatingType;
public class PerfRatingTypeDeleted implements Event{

	private boolean success;

	public PerfRatingTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
