package com.skytala.eCommerce.domain.content.relations.dataResource.event.purpose;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.purpose.DataResourcePurpose;
public class DataResourcePurposeFound implements Event{

	private List<DataResourcePurpose> dataResourcePurposes;

	public DataResourcePurposeFound(List<DataResourcePurpose> dataResourcePurposes) {
		this.dataResourcePurposes = dataResourcePurposes;
	}

	public List<DataResourcePurpose> getDataResourcePurposes()	{
		return dataResourcePurposes;
	}

}
