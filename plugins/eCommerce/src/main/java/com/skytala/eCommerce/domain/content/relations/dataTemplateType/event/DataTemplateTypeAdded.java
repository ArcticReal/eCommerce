package com.skytala.eCommerce.domain.content.relations.dataTemplateType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataTemplateType.model.DataTemplateType;
public class DataTemplateTypeAdded implements Event{

	private DataTemplateType addedDataTemplateType;
	private boolean success;

	public DataTemplateTypeAdded(DataTemplateType addedDataTemplateType, boolean success){
		this.addedDataTemplateType = addedDataTemplateType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DataTemplateType getAddedDataTemplateType() {
		return addedDataTemplateType;
	}

}
