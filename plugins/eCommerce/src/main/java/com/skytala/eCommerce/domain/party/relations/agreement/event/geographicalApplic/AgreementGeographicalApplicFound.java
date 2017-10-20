package com.skytala.eCommerce.domain.party.relations.agreement.event.geographicalApplic;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.geographicalApplic.AgreementGeographicalApplic;
public class AgreementGeographicalApplicFound implements Event{

	private List<AgreementGeographicalApplic> agreementGeographicalApplics;

	public AgreementGeographicalApplicFound(List<AgreementGeographicalApplic> agreementGeographicalApplics) {
		this.agreementGeographicalApplics = agreementGeographicalApplics;
	}

	public List<AgreementGeographicalApplic> getAgreementGeographicalApplics()	{
		return agreementGeographicalApplics;
	}

}
