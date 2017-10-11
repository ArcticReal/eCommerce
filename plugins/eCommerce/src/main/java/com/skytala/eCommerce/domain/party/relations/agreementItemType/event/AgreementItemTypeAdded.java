package com.skytala.eCommerce.domain.party.relations.agreementItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementItemType.model.AgreementItemType;
public class AgreementItemTypeAdded implements Event{

	private AgreementItemType addedAgreementItemType;
	private boolean success;

	public AgreementItemTypeAdded(AgreementItemType addedAgreementItemType, boolean success){
		this.addedAgreementItemType = addedAgreementItemType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementItemType getAddedAgreementItemType() {
		return addedAgreementItemType;
	}

}
