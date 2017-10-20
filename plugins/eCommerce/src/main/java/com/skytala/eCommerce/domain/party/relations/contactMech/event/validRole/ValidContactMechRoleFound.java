package com.skytala.eCommerce.domain.party.relations.contactMech.event.validRole;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.validRole.ValidContactMechRole;
public class ValidContactMechRoleFound implements Event{

	private List<ValidContactMechRole> validContactMechRoles;

	public ValidContactMechRoleFound(List<ValidContactMechRole> validContactMechRoles) {
		this.validContactMechRoles = validContactMechRoles;
	}

	public List<ValidContactMechRole> getValidContactMechRoles()	{
		return validContactMechRoles;
	}

}
