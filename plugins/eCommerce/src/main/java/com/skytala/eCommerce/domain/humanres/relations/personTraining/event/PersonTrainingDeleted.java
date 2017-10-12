package com.skytala.eCommerce.domain.humanres.relations.personTraining.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.personTraining.model.PersonTraining;
public class PersonTrainingDeleted implements Event{

	private boolean success;

	public PersonTrainingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
