package com.skytala.eCommerce.domain.party.relations.agreementItem.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementItem.model.AgreementItem;
public class AgreementItemFound implements Event{

	private List<AgreementItem> agreementItems;

	public AgreementItemFound(List<AgreementItem> agreementItems) {
		this.agreementItems = agreementItems;
	}

	public List<AgreementItem> getAgreementItems()	{
		return agreementItems;
	}

}
