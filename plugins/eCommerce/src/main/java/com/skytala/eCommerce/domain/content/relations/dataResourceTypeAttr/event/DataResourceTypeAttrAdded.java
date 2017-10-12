package com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.model.DataResourceTypeAttr;
public class DataResourceTypeAttrAdded implements Event{

	private DataResourceTypeAttr addedDataResourceTypeAttr;
	private boolean success;

	public DataResourceTypeAttrAdded(DataResourceTypeAttr addedDataResourceTypeAttr, boolean success){
		this.addedDataResourceTypeAttr = addedDataResourceTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DataResourceTypeAttr getAddedDataResourceTypeAttr() {
		return addedDataResourceTypeAttr;
	}

}
