package com.skytala.eCommerce.domain.content.relations.dataResource.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.type.DataResourceType;
public class DataResourceTypeFound implements Event{

	private List<DataResourceType> dataResourceTypes;

	public DataResourceTypeFound(List<DataResourceType> dataResourceTypes) {
		this.dataResourceTypes = dataResourceTypes;
	}

	public List<DataResourceType> getDataResourceTypes()	{
		return dataResourceTypes;
	}

}
