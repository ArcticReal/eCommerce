package com.skytala.eCommerce.domain.shipment.relations.picklistRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklistRole.model.PicklistRole;
public class PicklistRoleUpdated implements Event{

	private boolean success;

	public PicklistRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
