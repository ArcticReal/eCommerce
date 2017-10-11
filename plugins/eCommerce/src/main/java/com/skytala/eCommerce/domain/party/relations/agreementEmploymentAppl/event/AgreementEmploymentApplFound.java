package com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.model.AgreementEmploymentAppl;
public class AgreementEmploymentApplFound implements Event{

	private List<AgreementEmploymentAppl> agreementEmploymentAppls;

	public AgreementEmploymentApplFound(List<AgreementEmploymentAppl> agreementEmploymentAppls) {
		this.agreementEmploymentAppls = agreementEmploymentAppls;
	}

	public List<AgreementEmploymentAppl> getAgreementEmploymentAppls()	{
		return agreementEmploymentAppls;
	}

}
