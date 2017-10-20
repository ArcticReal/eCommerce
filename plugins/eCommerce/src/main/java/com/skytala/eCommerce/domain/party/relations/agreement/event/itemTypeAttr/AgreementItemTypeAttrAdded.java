package com.skytala.eCommerce.domain.party.relations.agreement.event.itemTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.itemTypeAttr.AgreementItemTypeAttr;
public class AgreementItemTypeAttrAdded implements Event{

	private AgreementItemTypeAttr addedAgreementItemTypeAttr;
	private boolean success;

	public AgreementItemTypeAttrAdded(AgreementItemTypeAttr addedAgreementItemTypeAttr, boolean success){
		this.addedAgreementItemTypeAttr = addedAgreementItemTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementItemTypeAttr getAddedAgreementItemTypeAttr() {
		return addedAgreementItemTypeAttr;
	}

}
