package com.skytala.eCommerce.domain.content.relations.dataCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataCategory.model.DataCategory;
public class DataCategoryAdded implements Event{

	private DataCategory addedDataCategory;
	private boolean success;

	public DataCategoryAdded(DataCategory addedDataCategory, boolean success){
		this.addedDataCategory = addedDataCategory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DataCategory getAddedDataCategory() {
		return addedDataCategory;
	}

}
