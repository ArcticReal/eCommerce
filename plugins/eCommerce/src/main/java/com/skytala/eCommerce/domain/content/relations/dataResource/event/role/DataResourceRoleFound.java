package com.skytala.eCommerce.domain.content.relations.dataResource.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.role.DataResourceRole;
public class DataResourceRoleFound implements Event{

	private List<DataResourceRole> dataResourceRoles;

	public DataResourceRoleFound(List<DataResourceRole> dataResourceRoles) {
		this.dataResourceRoles = dataResourceRoles;
	}

	public List<DataResourceRole> getDataResourceRoles()	{
		return dataResourceRoles;
	}

}
