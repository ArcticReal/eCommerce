package com.skytala.eCommerce.domain.party.relations.party.event.classificationGroup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.classificationGroup.PartyClassificationGroup;
public class PartyClassificationGroupAdded implements Event{

	private PartyClassificationGroup addedPartyClassificationGroup;
	private boolean success;

	public PartyClassificationGroupAdded(PartyClassificationGroup addedPartyClassificationGroup, boolean success){
		this.addedPartyClassificationGroup = addedPartyClassificationGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyClassificationGroup getAddedPartyClassificationGroup() {
		return addedPartyClassificationGroup;
	}

}
