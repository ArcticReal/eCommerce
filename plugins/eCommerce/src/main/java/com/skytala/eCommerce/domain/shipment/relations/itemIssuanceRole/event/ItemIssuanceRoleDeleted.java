package com.skytala.eCommerce.domain.shipment.relations.itemIssuanceRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.itemIssuanceRole.model.ItemIssuanceRole;
public class ItemIssuanceRoleDeleted implements Event{

	private boolean success;

	public ItemIssuanceRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
