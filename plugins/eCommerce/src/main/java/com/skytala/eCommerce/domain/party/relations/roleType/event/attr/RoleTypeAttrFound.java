package com.skytala.eCommerce.domain.party.relations.roleType.event.attr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.roleType.model.attr.RoleTypeAttr;
public class RoleTypeAttrFound implements Event{

	private List<RoleTypeAttr> roleTypeAttrs;

	public RoleTypeAttrFound(List<RoleTypeAttr> roleTypeAttrs) {
		this.roleTypeAttrs = roleTypeAttrs;
	}

	public List<RoleTypeAttr> getRoleTypeAttrs()	{
		return roleTypeAttrs;
	}

}
