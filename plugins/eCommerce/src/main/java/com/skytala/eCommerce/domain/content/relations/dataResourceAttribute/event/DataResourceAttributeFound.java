package com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.model.DataResourceAttribute;
public class DataResourceAttributeFound implements Event{

	private List<DataResourceAttribute> dataResourceAttributes;

	public DataResourceAttributeFound(List<DataResourceAttribute> dataResourceAttributes) {
		this.dataResourceAttributes = dataResourceAttributes;
	}

	public List<DataResourceAttribute> getDataResourceAttributes()	{
		return dataResourceAttributes;
	}

}
