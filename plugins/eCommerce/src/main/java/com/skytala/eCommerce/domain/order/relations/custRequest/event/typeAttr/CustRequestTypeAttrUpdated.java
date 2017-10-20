package com.skytala.eCommerce.domain.order.relations.custRequest.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.typeAttr.CustRequestTypeAttr;
public class CustRequestTypeAttrUpdated implements Event{

	private boolean success;

	public CustRequestTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
