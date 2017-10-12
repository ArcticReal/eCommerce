package com.skytala.eCommerce.domain.humanres.relations.trainingRequest.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.model.TrainingRequest;
public class TrainingRequestDeleted implements Event{

	private boolean success;

	public TrainingRequestDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
