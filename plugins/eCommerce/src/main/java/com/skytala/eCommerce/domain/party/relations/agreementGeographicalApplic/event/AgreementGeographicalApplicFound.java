package com.skytala.eCommerce.domain.party.relations.agreementGeographicalApplic.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementGeographicalApplic.model.AgreementGeographicalApplic;
public class AgreementGeographicalApplicFound implements Event{

	private List<AgreementGeographicalApplic> agreementGeographicalApplics;

	public AgreementGeographicalApplicFound(List<AgreementGeographicalApplic> agreementGeographicalApplics) {
		this.agreementGeographicalApplics = agreementGeographicalApplics;
	}

	public List<AgreementGeographicalApplic> getAgreementGeographicalApplics()	{
		return agreementGeographicalApplics;
	}

}
