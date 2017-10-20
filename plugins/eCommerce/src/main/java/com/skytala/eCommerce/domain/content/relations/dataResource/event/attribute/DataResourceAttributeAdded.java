package com.skytala.eCommerce.domain.content.relations.dataResource.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.attribute.DataResourceAttribute;
public class DataResourceAttributeAdded implements Event{

	private DataResourceAttribute addedDataResourceAttribute;
	private boolean success;

	public DataResourceAttributeAdded(DataResourceAttribute addedDataResourceAttribute, boolean success){
		this.addedDataResourceAttribute = addedDataResourceAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DataResourceAttribute getAddedDataResourceAttribute() {
		return addedDataResourceAttribute;
	}

}
