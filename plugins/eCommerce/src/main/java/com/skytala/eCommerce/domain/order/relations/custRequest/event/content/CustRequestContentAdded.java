package com.skytala.eCommerce.domain.order.relations.custRequest.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.content.CustRequestContent;
public class CustRequestContentAdded implements Event{

	private CustRequestContent addedCustRequestContent;
	private boolean success;

	public CustRequestContentAdded(CustRequestContent addedCustRequestContent, boolean success){
		this.addedCustRequestContent = addedCustRequestContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestContent getAddedCustRequestContent() {
		return addedCustRequestContent;
	}

}
