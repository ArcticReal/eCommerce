package com.skytala.eCommerce.domain.content.relations.dataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.DataResource;
public class DataResourceAdded implements Event{

	private DataResource addedDataResource;
	private boolean success;

	public DataResourceAdded(DataResource addedDataResource, boolean success){
		this.addedDataResource = addedDataResource;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DataResource getAddedDataResource() {
		return addedDataResource;
	}

}
