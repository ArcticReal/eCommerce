package com.skytala.eCommerce.domain.order.relations.custRequest.event.category;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.category.CustRequestCategory;
public class CustRequestCategoryAdded implements Event{

	private CustRequestCategory addedCustRequestCategory;
	private boolean success;

	public CustRequestCategoryAdded(CustRequestCategory addedCustRequestCategory, boolean success){
		this.addedCustRequestCategory = addedCustRequestCategory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestCategory getAddedCustRequestCategory() {
		return addedCustRequestCategory;
	}

}
