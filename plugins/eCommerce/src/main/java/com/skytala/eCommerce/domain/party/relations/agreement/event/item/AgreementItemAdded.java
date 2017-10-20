package com.skytala.eCommerce.domain.party.relations.agreement.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.item.AgreementItem;
public class AgreementItemAdded implements Event{

	private AgreementItem addedAgreementItem;
	private boolean success;

	public AgreementItemAdded(AgreementItem addedAgreementItem, boolean success){
		this.addedAgreementItem = addedAgreementItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementItem getAddedAgreementItem() {
		return addedAgreementItem;
	}

}
