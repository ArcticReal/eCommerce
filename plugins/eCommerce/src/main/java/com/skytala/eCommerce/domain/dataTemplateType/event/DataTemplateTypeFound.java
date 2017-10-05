package com.skytala.eCommerce.domain.dataTemplateType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.dataTemplateType.model.DataTemplateType;
public class DataTemplateTypeFound implements Event{

	private List<DataTemplateType> dataTemplateTypes;

	public DataTemplateTypeFound(List<DataTemplateType> dataTemplateTypes) {
		this.dataTemplateTypes = dataTemplateTypes;
	}

	public List<DataTemplateType> getDataTemplateTypes()	{
		return dataTemplateTypes;
	}

}
