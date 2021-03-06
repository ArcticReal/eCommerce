package com.skytala.eCommerce.domain.party.relations.agreement.event.itemAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.itemAttribute.AgreementItemAttribute;
public class AgreementItemAttributeAdded implements Event{

	private AgreementItemAttribute addedAgreementItemAttribute;
	private boolean success;

	public AgreementItemAttributeAdded(AgreementItemAttribute addedAgreementItemAttribute, boolean success){
		this.addedAgreementItemAttribute = addedAgreementItemAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementItemAttribute getAddedAgreementItemAttribute() {
		return addedAgreementItemAttribute;
	}

}
