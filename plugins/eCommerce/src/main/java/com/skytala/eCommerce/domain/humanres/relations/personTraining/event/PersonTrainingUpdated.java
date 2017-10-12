package com.skytala.eCommerce.domain.humanres.relations.personTraining.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.personTraining.model.PersonTraining;
public class PersonTrainingUpdated implements Event{

	private boolean success;

	public PersonTrainingUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
