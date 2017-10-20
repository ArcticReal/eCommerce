package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.role.FinAccountRole;
public class FinAccountRoleFound implements Event{

	private List<FinAccountRole> finAccountRoles;

	public FinAccountRoleFound(List<FinAccountRole> finAccountRoles) {
		this.finAccountRoles = finAccountRoles;
	}

	public List<FinAccountRole> getFinAccountRoles()	{
		return finAccountRoles;
	}

}
