package com.skytala.eCommerce.domain.party.relations.agreement.event.employmentAppl;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.employmentAppl.AgreementEmploymentAppl;
public class AgreementEmploymentApplFound implements Event{

	private List<AgreementEmploymentAppl> agreementEmploymentAppls;

	public AgreementEmploymentApplFound(List<AgreementEmploymentAppl> agreementEmploymentAppls) {
		this.agreementEmploymentAppls = agreementEmploymentAppls;
	}

	public List<AgreementEmploymentAppl> getAgreementEmploymentAppls()	{
		return agreementEmploymentAppls;
	}

}
