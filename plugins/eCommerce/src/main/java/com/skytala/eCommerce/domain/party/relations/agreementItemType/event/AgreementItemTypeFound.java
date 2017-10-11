package com.skytala.eCommerce.domain.party.relations.agreementItemType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementItemType.model.AgreementItemType;
public class AgreementItemTypeFound implements Event{

	private List<AgreementItemType> agreementItemTypes;

	public AgreementItemTypeFound(List<AgreementItemType> agreementItemTypes) {
		this.agreementItemTypes = agreementItemTypes;
	}

	public List<AgreementItemType> getAgreementItemTypes()	{
		return agreementItemTypes;
	}

}
