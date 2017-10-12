package com.skytala.eCommerce.domain.shipment.relations.itemIssuanceRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.itemIssuanceRole.model.ItemIssuanceRole;
public class ItemIssuanceRoleFound implements Event{

	private List<ItemIssuanceRole> itemIssuanceRoles;

	public ItemIssuanceRoleFound(List<ItemIssuanceRole> itemIssuanceRoles) {
		this.itemIssuanceRoles = itemIssuanceRoles;
	}

	public List<ItemIssuanceRole> getItemIssuanceRoles()	{
		return itemIssuanceRoles;
	}

}
