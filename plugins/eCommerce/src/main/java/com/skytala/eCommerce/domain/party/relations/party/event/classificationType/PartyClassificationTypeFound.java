package com.skytala.eCommerce.domain.party.relations.party.event.classificationType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.classificationType.PartyClassificationType;
public class PartyClassificationTypeFound implements Event{

	private List<PartyClassificationType> partyClassificationTypes;

	public PartyClassificationTypeFound(List<PartyClassificationType> partyClassificationTypes) {
		this.partyClassificationTypes = partyClassificationTypes;
	}

	public List<PartyClassificationType> getPartyClassificationTypes()	{
		return partyClassificationTypes;
	}

}
