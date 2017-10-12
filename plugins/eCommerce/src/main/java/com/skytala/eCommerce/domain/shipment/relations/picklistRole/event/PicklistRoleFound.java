package com.skytala.eCommerce.domain.shipment.relations.picklistRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklistRole.model.PicklistRole;
public class PicklistRoleFound implements Event{

	private List<PicklistRole> picklistRoles;

	public PicklistRoleFound(List<PicklistRole> picklistRoles) {
		this.picklistRoles = picklistRoles;
	}

	public List<PicklistRole> getPicklistRoles()	{
		return picklistRoles;
	}

}
