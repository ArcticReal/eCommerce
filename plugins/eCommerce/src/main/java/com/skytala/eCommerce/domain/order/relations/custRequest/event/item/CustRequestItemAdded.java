package com.skytala.eCommerce.domain.order.relations.custRequest.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.item.CustRequestItem;
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
