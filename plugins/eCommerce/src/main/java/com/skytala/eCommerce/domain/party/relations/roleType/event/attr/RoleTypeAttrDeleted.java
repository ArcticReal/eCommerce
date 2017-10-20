package com.skytala.eCommerce.domain.party.relations.roleType.event.attr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.roleType.model.attr.RoleTypeAttr;
public class RoleTypeAttrDeleted implements Event{

	private boolean success;

	public RoleTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
