package com.skytala.eCommerce.domain.party.relations.contactMech.event.validRole;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.validRole.ValidContactMechRole;
public class ValidContactMechRoleAdded implements Event{

	private ValidContactMechRole addedValidContactMechRole;
	private boolean success;

	public ValidContactMechRoleAdded(ValidContactMechRole addedValidContactMechRole, boolean success){
		this.addedValidContactMechRole = addedValidContactMechRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ValidContactMechRole getAddedValidContactMechRole() {
		return addedValidContactMechRole;
	}

}
