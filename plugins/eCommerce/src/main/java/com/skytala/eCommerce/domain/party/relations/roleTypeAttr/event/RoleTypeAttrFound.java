package com.skytala.eCommerce.domain.party.relations.roleTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.model.RoleTypeAttr;
public class RoleTypeAttrFound implements Event{

	private List<RoleTypeAttr> roleTypeAttrs;

	public RoleTypeAttrFound(List<RoleTypeAttr> roleTypeAttrs) {
		this.roleTypeAttrs = roleTypeAttrs;
	}

	public List<RoleTypeAttr> getRoleTypeAttrs()	{
		return roleTypeAttrs;
	}

}
