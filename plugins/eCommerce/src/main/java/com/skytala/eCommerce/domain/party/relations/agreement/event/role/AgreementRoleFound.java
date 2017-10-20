package com.skytala.eCommerce.domain.party.relations.agreement.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.agreement.model.role.AgreementRole;
public class AgreementRoleFound implements Event{

	private List<AgreementRole> agreementRoles;

	public AgreementRoleFound(List<AgreementRole> agreementRoles) {
		this.agreementRoles = agreementRoles;
	}

	public List<AgreementRole> getAgreementRoles()	{
		return agreementRoles;
	}

}
