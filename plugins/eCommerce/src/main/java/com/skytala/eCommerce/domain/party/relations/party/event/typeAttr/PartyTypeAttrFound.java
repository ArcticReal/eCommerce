package com.skytala.eCommerce.domain.party.relations.party.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.typeAttr.PartyTypeAttr;
public class PartyTypeAttrFound implements Event{

	private List<PartyTypeAttr> partyTypeAttrs;

	public PartyTypeAttrFound(List<PartyTypeAttr> partyTypeAttrs) {
		this.partyTypeAttrs = partyTypeAttrs;
	}

	public List<PartyTypeAttr> getPartyTypeAttrs()	{
		return partyTypeAttrs;
	}

}
