package com.skytala.eCommerce.domain.shipment.relations.picklist.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklist.model.role.PicklistRole;
public class PicklistRoleAdded implements Event{

	private PicklistRole addedPicklistRole;
	private boolean success;

	public PicklistRoleAdded(PicklistRole addedPicklistRole, boolean success){
		this.addedPicklistRole = addedPicklistRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PicklistRole getAddedPicklistRole() {
		return addedPicklistRole;
	}

}
