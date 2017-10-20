package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.role.ItemIssuanceRole;
public class ItemIssuanceRoleFound implements Event{

	private List<ItemIssuanceRole> itemIssuanceRoles;

	public ItemIssuanceRoleFound(List<ItemIssuanceRole> itemIssuanceRoles) {
		this.itemIssuanceRoles = itemIssuanceRoles;
	}

	public List<ItemIssuanceRole> getItemIssuanceRoles()	{
		return itemIssuanceRoles;
	}

}
