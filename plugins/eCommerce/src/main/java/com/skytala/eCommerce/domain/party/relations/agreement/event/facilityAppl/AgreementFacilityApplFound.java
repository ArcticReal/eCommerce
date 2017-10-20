package com.skytala.eCommerce.domain.party.relations.agreement.event.facilityAppl;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.facilityAppl.AgreementFacilityAppl;
public class AgreementFacilityApplFound implements Event{

	private List<AgreementFacilityAppl> agreementFacilityAppls;

	public AgreementFacilityApplFound(List<AgreementFacilityAppl> agreementFacilityAppls) {
		this.agreementFacilityAppls = agreementFacilityAppls;
	}

	public List<AgreementFacilityAppl> getAgreementFacilityAppls()	{
		return agreementFacilityAppls;
	}

}
