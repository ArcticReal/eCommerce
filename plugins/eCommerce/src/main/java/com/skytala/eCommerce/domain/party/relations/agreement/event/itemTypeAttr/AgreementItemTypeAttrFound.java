package com.skytala.eCommerce.domain.party.relations.agreement.event.itemTypeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.itemTypeAttr.AgreementItemTypeAttr;
public class AgreementItemTypeAttrFound implements Event{

	private List<AgreementItemTypeAttr> agreementItemTypeAttrs;

	public AgreementItemTypeAttrFound(List<AgreementItemTypeAttr> agreementItemTypeAttrs) {
		this.agreementItemTypeAttrs = agreementItemTypeAttrs;
	}

	public List<AgreementItemTypeAttr> getAgreementItemTypeAttrs()	{
		return agreementItemTypeAttrs;
	}

}
