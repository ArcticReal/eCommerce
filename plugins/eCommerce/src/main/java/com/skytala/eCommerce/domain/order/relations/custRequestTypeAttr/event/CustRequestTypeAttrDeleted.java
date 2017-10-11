package com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.model.CustRequestTypeAttr;
public class CustRequestTypeAttrDeleted implements Event{

	private boolean success;

	public CustRequestTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
