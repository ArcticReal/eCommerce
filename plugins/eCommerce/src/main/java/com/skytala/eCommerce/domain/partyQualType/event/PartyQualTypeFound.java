package com.skytala.eCommerce.domain.partyQualType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyQualType.model.PartyQualType;
public class PartyQualTypeFound implements Event{

	private List<PartyQualType> partyQualTypes;

	public PartyQualTypeFound(List<PartyQualType> partyQualTypes) {
		this.partyQualTypes = partyQualTypes;
	}

	public List<PartyQualType> getPartyQualTypes()	{
		return partyQualTypes;
	}

}
