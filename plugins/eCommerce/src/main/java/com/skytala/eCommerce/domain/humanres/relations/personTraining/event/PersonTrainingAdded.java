package com.skytala.eCommerce.domain.humanres.relations.personTraining.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.personTraining.model.PersonTraining;
public class PersonTrainingAdded implements Event{

	private PersonTraining addedPersonTraining;
	private boolean success;

	public PersonTrainingAdded(PersonTraining addedPersonTraining, boolean success){
		this.addedPersonTraining = addedPersonTraining;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PersonTraining getAddedPersonTraining() {
		return addedPersonTraining;
	}

}
