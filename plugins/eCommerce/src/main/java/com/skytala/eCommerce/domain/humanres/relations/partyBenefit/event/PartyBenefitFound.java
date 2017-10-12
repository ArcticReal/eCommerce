package com.skytala.eCommerce.domain.humanres.relations.partyBenefit.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.model.PartyBenefit;
public class PartyBenefitFound implements Event{

	private List<PartyBenefit> partyBenefits;

	public PartyBenefitFound(List<PartyBenefit> partyBenefits) {
		this.partyBenefits = partyBenefits;
	}

	public List<PartyBenefit> getPartyBenefits()	{
		return partyBenefits;
	}

}
