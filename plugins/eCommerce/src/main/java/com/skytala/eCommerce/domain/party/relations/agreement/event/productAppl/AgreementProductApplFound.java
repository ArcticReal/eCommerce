package com.skytala.eCommerce.domain.party.relations.agreement.event.productAppl;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.productAppl.AgreementProductAppl;
public class AgreementProductApplFound implements Event{

	private List<AgreementProductAppl> agreementProductAppls;

	public AgreementProductApplFound(List<AgreementProductAppl> agreementProductAppls) {
		this.agreementProductAppls = agreementProductAppls;
	}

	public List<AgreementProductAppl> getAgreementProductAppls()	{
		return agreementProductAppls;
	}

}
