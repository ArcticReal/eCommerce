package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.role.ItemIssuanceRole;
public class ItemIssuanceRoleUpdated implements Event{

	private boolean success;

	public ItemIssuanceRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
