package com.skytala.eCommerce.domain.content.relations.dataResource.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.DataResource;
public class DataResourceFound implements Event{

	private List<DataResource> dataResources;

	public DataResourceFound(List<DataResource> dataResources) {
		this.dataResources = dataResources;
	}

	public List<DataResource> getDataResources()	{
		return dataResources;
	}

}
