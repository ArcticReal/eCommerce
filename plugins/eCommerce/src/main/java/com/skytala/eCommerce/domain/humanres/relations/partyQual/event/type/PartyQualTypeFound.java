package com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.type.PartyQualType;
public class PartyQualTypeFound implements Event{

	private List<PartyQualType> partyQualTypes;

	public PartyQualTypeFound(List<PartyQualType> partyQualTypes) {
		this.partyQualTypes = partyQualTypes;
	}

	public List<PartyQualType> getPartyQualTypes()	{
		return partyQualTypes;
	}

}
