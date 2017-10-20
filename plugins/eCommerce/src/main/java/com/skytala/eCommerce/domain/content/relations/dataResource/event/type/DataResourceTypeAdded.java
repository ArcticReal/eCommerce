package com.skytala.eCommerce.domain.content.relations.dataResource.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.type.DataResourceType;
public class DataResourceTypeAdded implements Event{

	private DataResourceType addedDataResourceType;
	private boolean success;

	public DataResourceTypeAdded(DataResourceType addedDataResourceType, boolean success){
		this.addedDataResourceType = addedDataResourceType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DataResourceType getAddedDataResourceType() {
		return addedDataResourceType;
	}

}
