package com.skytala.eCommerce.domain.humanres.relations.trainingClassType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.model.TrainingClassType;
public class TrainingClassTypeAdded implements Event{

	private TrainingClassType addedTrainingClassType;
	private boolean success;

	public TrainingClassTypeAdded(TrainingClassType addedTrainingClassType, boolean success){
		this.addedTrainingClassType = addedTrainingClassType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TrainingClassType getAddedTrainingClassType() {
		return addedTrainingClassType;
	}

}
