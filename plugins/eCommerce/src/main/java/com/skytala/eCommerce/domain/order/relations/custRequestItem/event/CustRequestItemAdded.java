package com.skytala.eCommerce.domain.order.relations.custRequestItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestItem.model.CustRequestItem;
public class CustRequestItemAdded implements Event{

	private CustRequestItem addedCustRequestItem;
	private boolean success;

	public CustRequestItemAdded(CustRequestItem addedCustRequestItem, boolean success){
		this.addedCustRequestItem = addedCustRequestItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestItem getAddedCustRequestItem() {
		return addedCustRequestItem;
	}

}
