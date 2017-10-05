package com.skytala.eCommerce.domain.trainingRequest.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.trainingRequest.model.TrainingRequest;
public class TrainingRequestFound implements Event{

	private List<TrainingRequest> trainingRequests;

	public TrainingRequestFound(List<TrainingRequest> trainingRequests) {
		this.trainingRequests = trainingRequests;
	}

	public List<TrainingRequest> getTrainingRequests()	{
		return trainingRequests;
	}

}
