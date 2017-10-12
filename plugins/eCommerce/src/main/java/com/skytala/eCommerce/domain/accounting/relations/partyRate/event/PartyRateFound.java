package com.skytala.eCommerce.domain.accounting.relations.partyRate.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyRate.model.PartyRate;
public class PartyRateFound implements Event{

	private List<PartyRate> partyRates;

	public PartyRateFound(List<PartyRate> partyRates) {
		this.partyRates = partyRates;
	}

	public List<PartyRate> getPartyRates()	{
		return partyRates;
	}

}
