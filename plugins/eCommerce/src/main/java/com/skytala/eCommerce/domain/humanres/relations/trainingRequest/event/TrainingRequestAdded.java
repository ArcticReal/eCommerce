package com.skytala.eCommerce.domain.humanres.relations.trainingRequest.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.model.TrainingRequest;
public class TrainingRequestAdded implements Event{

	private TrainingRequest addedTrainingRequest;
	private boolean success;

	public TrainingRequestAdded(TrainingRequest addedTrainingRequest, boolean success){
		this.addedTrainingRequest = addedTrainingRequest;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TrainingRequest getAddedTrainingRequest() {
		return addedTrainingRequest;
	}

}
