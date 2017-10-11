package com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.model.AgreementItemTypeAttr;
public class AgreementItemTypeAttrFound implements Event{

	private List<AgreementItemTypeAttr> agreementItemTypeAttrs;

	public AgreementItemTypeAttrFound(List<AgreementItemTypeAttr> agreementItemTypeAttrs) {
		this.agreementItemTypeAttrs = agreementItemTypeAttrs;
	}

	public List<AgreementItemTypeAttr> getAgreementItemTypeAttrs()	{
		return agreementItemTypeAttrs;
	}

}
