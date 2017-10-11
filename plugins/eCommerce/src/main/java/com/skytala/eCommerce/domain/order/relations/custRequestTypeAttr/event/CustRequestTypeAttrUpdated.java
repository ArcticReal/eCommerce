package com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.model.CustRequestTypeAttr;
public class CustRequestTypeAttrUpdated implements Event{

	private boolean success;

	public CustRequestTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
