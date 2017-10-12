package com.skytala.eCommerce.domain.accounting.relations.finAccountRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.model.FinAccountRole;
public class FinAccountRoleFound implements Event{

	private List<FinAccountRole> finAccountRoles;

	public FinAccountRoleFound(List<FinAccountRole> finAccountRoles) {
		this.finAccountRoles = finAccountRoles;
	}

	public List<FinAccountRole> getFinAccountRoles()	{
		return finAccountRoles;
	}

}
