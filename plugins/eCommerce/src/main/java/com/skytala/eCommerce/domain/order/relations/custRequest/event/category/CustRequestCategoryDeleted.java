package com.skytala.eCommerce.domain.order.relations.custRequest.event.category;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.category.CustRequestCategory;
public class CustRequestCategoryDeleted implements Event{

	private boolean success;

	public CustRequestCategoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}