package com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.model.DataResourcePurpose;
public class DataResourcePurposeAdded implements Event{

	private DataResourcePurpose addedDataResourcePurpose;
	private boolean success;

	public DataResourcePurposeAdded(DataResourcePurpose addedDataResourcePurpose, boolean success){
		this.addedDataResourcePurpose = addedDataResourcePurpose;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DataResourcePurpose getAddedDataResourcePurpose() {
		return addedDataResourcePurpose;
	}

}
