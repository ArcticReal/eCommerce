package com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.model.DataResourcePurpose;
public class DataResourcePurposeFound implements Event{

	private List<DataResourcePurpose> dataResourcePurposes;

	public DataResourcePurposeFound(List<DataResourcePurpose> dataResourcePurposes) {
		this.dataResourcePurposes = dataResourcePurposes;
	}

	public List<DataResourcePurpose> getDataResourcePurposes()	{
		return dataResourcePurposes;
	}

}
