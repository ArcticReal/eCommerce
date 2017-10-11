package com.skytala.eCommerce.domain.party.relations.agreementGeographicalApplic.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementGeographicalApplic.model.AgreementGeographicalApplic;
public class AgreementGeographicalApplicAdded implements Event{

	private AgreementGeographicalApplic addedAgreementGeographicalApplic;
	private boolean success;

	public AgreementGeographicalApplicAdded(AgreementGeographicalApplic addedAgreementGeographicalApplic, boolean success){
		this.addedAgreementGeographicalApplic = addedAgreementGeographicalApplic;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementGeographicalApplic getAddedAgreementGeographicalApplic() {
		return addedAgreementGeographicalApplic;
	}

}
