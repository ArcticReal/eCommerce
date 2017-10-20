package com.skytala.eCommerce.domain.party.relations.agreement.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.typeAttr.AgreementTypeAttr;
public class AgreementTypeAttrAdded implements Event{

	private AgreementTypeAttr addedAgreementTypeAttr;
	private boolean success;

	public AgreementTypeAttrAdded(AgreementTypeAttr addedAgreementTypeAttr, boolean success){
		this.addedAgreementTypeAttr = addedAgreementTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementTypeAttr getAddedAgreementTypeAttr() {
		return addedAgreementTypeAttr;
	}

}
