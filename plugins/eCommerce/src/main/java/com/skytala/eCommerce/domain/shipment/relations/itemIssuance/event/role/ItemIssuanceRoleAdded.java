package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.role.ItemIssuanceRole;
public class ItemIssuanceRoleAdded implements Event{

	private ItemIssuanceRole addedItemIssuanceRole;
	private boolean success;

	public ItemIssuanceRoleAdded(ItemIssuanceRole addedItemIssuanceRole, boolean success){
		this.addedItemIssuanceRole = addedItemIssuanceRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ItemIssuanceRole getAddedItemIssuanceRole() {
		return addedItemIssuanceRole;
	}

}
