package com.skytala.eCommerce.domain.party.relations.roleType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.roleType.model.RoleType;
public class RoleTypeFound implements Event{

	private List<RoleType> roleTypes;

	public RoleTypeFound(List<RoleType> roleTypes) {
		this.roleTypes = roleTypes;
	}

	public List<RoleType> getRoleTypes()	{
		return roleTypes;
	}

}
