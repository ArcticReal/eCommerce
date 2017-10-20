package com.skytala.eCommerce.domain.content.relations.dataResource.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.role.DataResourceRole;
public class DataResourceRoleAdded implements Event{

	private DataResourceRole addedDataResourceRole;
	private boolean success;

	public DataResourceRoleAdded(DataResourceRole addedDataResourceRole, boolean success){
		this.addedDataResourceRole = addedDataResourceRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DataResourceRole getAddedDataResourceRole() {
		return addedDataResourceRole;
	}

}
