package com.skytala.eCommerce.domain.humanres.relations.partyQual.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.PartyQual;
public class PartyQualFound implements Event{

	private List<PartyQual> partyQuals;

	public PartyQualFound(List<PartyQual> partyQuals) {
		this.partyQuals = partyQuals;
	}

	public List<PartyQual> getPartyQuals()	{
		return partyQuals;
	}

}
