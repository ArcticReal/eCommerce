package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.role.FinAccountRole;
public class FinAccountRoleAdded implements Event{

	private FinAccountRole addedFinAccountRole;
	private boolean success;

	public FinAccountRoleAdded(FinAccountRole addedFinAccountRole, boolean success){
		this.addedFinAccountRole = addedFinAccountRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccountRole getAddedFinAccountRole() {
		return addedFinAccountRole;
	}

}
