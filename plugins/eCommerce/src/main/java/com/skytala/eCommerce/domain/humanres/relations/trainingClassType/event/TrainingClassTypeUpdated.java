package com.skytala.eCommerce.domain.humanres.relations.trainingClassType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.model.TrainingClassType;
public class TrainingClassTypeUpdated implements Event{

	private boolean success;

	public TrainingClassTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}