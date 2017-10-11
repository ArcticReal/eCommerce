package com.skytala.eCommerce.domain.party.relations.roleTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.model.RoleTypeAttr;
public class RoleTypeAttrDeleted implements Event{

	private boolean success;

	public RoleTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
