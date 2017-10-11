package com.skytala.eCommerce.domain.order.relations.custRequestCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestCategory.model.CustRequestCategory;
public class CustRequestCategoryUpdated implements Event{

	private boolean success;

	public CustRequestCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
