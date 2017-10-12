package com.skytala.eCommerce.domain.content.relations.dataCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataCategory.model.DataCategory;
public class DataCategoryUpdated implements Event{

	private boolean success;

	public DataCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
