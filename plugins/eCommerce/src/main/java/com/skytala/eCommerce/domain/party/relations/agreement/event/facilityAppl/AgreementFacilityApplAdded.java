package com.skytala.eCommerce.domain.party.relations.agreement.event.facilityAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.facilityAppl.AgreementFacilityAppl;
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
