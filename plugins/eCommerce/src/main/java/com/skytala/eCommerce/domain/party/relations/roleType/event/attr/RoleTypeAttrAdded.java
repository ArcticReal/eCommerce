package com.skytala.eCommerce.domain.party.relations.roleType.event.attr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.roleType.model.attr.RoleTypeAttr;
public class RoleTypeAttrAdded implements Event{

	private RoleTypeAttr addedRoleTypeAttr;
	private boolean success;

	public RoleTypeAttrAdded(RoleTypeAttr addedRoleTypeAttr, boolean success){
		this.addedRoleTypeAttr = addedRoleTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RoleTypeAttr getAddedRoleTypeAttr() {
		return addedRoleTypeAttr;
	}

}
