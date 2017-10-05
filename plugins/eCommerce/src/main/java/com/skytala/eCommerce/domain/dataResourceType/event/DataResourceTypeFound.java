package com.skytala.eCommerce.domain.dataResourceType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.dataResourceType.model.DataResourceType;
public class DataResourceTypeFound implements Event{

	private List<DataResourceType> dataResourceTypes;

	public DataResourceTypeFound(List<DataResourceType> dataResourceTypes) {
		this.dataResourceTypes = dataResourceTypes;
	}

	public List<DataResourceType> getDataResourceTypes()	{
		return dataResourceTypes;
	}

}
