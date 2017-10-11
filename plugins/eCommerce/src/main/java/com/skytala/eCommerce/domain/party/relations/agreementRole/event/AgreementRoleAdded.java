package com.skytala.eCommerce.domain.party.relations.agreementRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreementRole.model.AgreementRole;
public class AgreementRoleAdded implements Event{

	private AgreementRole addedAgreementRole;
	private boolean success;

	public AgreementRoleAdded(AgreementRole addedAgreementRole, boolean success){
		this.addedAgreementRole = addedAgreementRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AgreementRole getAddedAgreementRole() {
		return addedAgreementRole;
	}

}
