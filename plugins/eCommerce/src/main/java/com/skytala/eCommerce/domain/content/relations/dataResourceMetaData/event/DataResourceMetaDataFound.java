package com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.model.DataResourceMetaData;
public class DataResourceMetaDataFound implements Event{

	private List<DataResourceMetaData> dataResourceMetaDatas;

	public DataResourceMetaDataFound(List<DataResourceMetaData> dataResourceMetaDatas) {
		this.dataResourceMetaDatas = dataResourceMetaDatas;
	}

	public List<DataResourceMetaData> getDataResourceMetaDatas()	{
		return dataResourceMetaDatas;
	}

}
