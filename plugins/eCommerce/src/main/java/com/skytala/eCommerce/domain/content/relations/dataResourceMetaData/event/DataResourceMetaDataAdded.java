package com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.model.DataResourceMetaData;
public class DataResourceMetaDataAdded implements Event{

	private DataResourceMetaData addedDataResourceMetaData;
	private boolean success;

	public DataResourceMetaDataAdded(DataResourceMetaData addedDataResourceMetaData, boolean success){
		this.addedDataResourceMetaData = addedDataResourceMetaData;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DataResourceMetaData getAddedDataResourceMetaData() {
		return addedDataResourceMetaData;
	}

}
