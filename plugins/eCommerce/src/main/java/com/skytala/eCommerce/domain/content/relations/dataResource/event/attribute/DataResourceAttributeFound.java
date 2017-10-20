package com.skytala.eCommerce.domain.content.relations.dataResource.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.attribute.DataResourceAttribute;
public class DataResourceAttributeFound implements Event{

	private List<DataResourceAttribute> dataResourceAttributes;

	public DataResourceAttributeFound(List<DataResourceAttribute> dataResourceAttributes) {
		this.dataResourceAttributes = dataResourceAttributes;
	}

	public List<DataResourceAttribute> getDataResourceAttributes()	{
		return dataResourceAttributes;
	}

}
