package com.skytala.eCommerce.domain.trainingClassType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.trainingClassType.model.TrainingClassType;
public class TrainingClassTypeFound implements Event{

	private List<TrainingClassType> trainingClassTypes;

	public TrainingClassTypeFound(List<TrainingClassType> trainingClassTypes) {
		this.trainingClassTypes = trainingClassTypes;
	}

	public List<TrainingClassType> getTrainingClassTypes()	{
		return trainingClassTypes;
	}

}
