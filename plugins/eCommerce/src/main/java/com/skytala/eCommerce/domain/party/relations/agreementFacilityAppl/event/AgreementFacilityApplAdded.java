package com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.model.AgreementFacilityAppl;
public class AgreementFacilityApplAdded implements Event{

	private AgreementFacilityAppl addedAgreementFacilityAppl;
	private boolean success;

	public AgreementFacilityApplAdded(AgreementFacilityAppl addedAgreementFacilityAppl, boolean success){
		this.addedAgreementFacilityAppl = addedAgreementFacilityAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementFacilityAppl getAddedAgreementFacilityAppl() {
		return addedAgreementFacilityAppl;
	}

}
