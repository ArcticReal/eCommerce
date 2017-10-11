package com.skytala.eCommerce.domain.party.relations.roleTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.model.RoleTypeAttr;
public class RoleTypeAttrUpdated implements Event{

	private boolean success;

	public RoleTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
