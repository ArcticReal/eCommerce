package com.skytala.eCommerce.domain.humanres.relations.personTraining.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.personTraining.model.PersonTraining;
public class PersonTrainingFound implements Event{

	private List<PersonTraining> personTrainings;

	public PersonTrainingFound(List<PersonTraining> personTrainings) {
		this.personTrainings = personTrainings;
	}

	public List<PersonTraining> getPersonTrainings()	{
		return personTrainings;
	}

}
