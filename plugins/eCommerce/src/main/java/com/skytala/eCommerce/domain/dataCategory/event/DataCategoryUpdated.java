package com.skytala.eCommerce.domain.dataCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.dataCategory.model.DataCategory;
public class DataCategoryUpdated implements Event{

	private boolean success;

	public DataCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
