package com.skytala.eCommerce.domain.shipment.relations.picklistRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklistRole.model.PicklistRole;
public class PicklistRoleDeleted implements Event{

	private boolean success;

	public PicklistRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
