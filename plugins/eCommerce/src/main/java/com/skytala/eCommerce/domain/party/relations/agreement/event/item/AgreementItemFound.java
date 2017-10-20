package com.skytala.eCommerce.domain.party.relations.agreement.event.item;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.item.AgreementItem;
public class AgreementItemFound implements Event{

	private List<AgreementItem> agreementItems;

	public AgreementItemFound(List<AgreementItem> agreementItems) {
		this.agreementItems = agreementItems;
	}

	public List<AgreementItem> getAgreementItems()	{
		return agreementItems;
	}

}
