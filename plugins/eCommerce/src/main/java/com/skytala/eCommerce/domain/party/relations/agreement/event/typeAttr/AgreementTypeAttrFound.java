package com.skytala.eCommerce.domain.party.relations.agreement.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.typeAttr.AgreementTypeAttr;
public class AgreementTypeAttrFound implements Event{

	private List<AgreementTypeAttr> agreementTypeAttrs;

	public AgreementTypeAttrFound(List<AgreementTypeAttr> agreementTypeAttrs) {
		this.agreementTypeAttrs = agreementTypeAttrs;
	}

	public List<AgreementTypeAttr> getAgreementTypeAttrs()	{
		return agreementTypeAttrs;
	}

}
