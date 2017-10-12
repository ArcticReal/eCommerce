package com.skytala.eCommerce.domain.humanres.relations.trainingRequest.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.model.TrainingRequest;
public class TrainingRequestUpdated implements Event{

	private boolean success;

	public TrainingRequestUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
