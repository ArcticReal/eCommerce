package com.skytala.eCommerce.domain.order.relations.custRequest.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.typeAttr.CustRequestTypeAttr;
public class CustRequestTypeAttrAdded implements Event{

	private CustRequestTypeAttr addedCustRequestTypeAttr;
	private boolean success;

	public CustRequestTypeAttrAdded(CustRequestTypeAttr addedCustRequestTypeAttr, boolean success){
		this.addedCustRequestTypeAttr = addedCustRequestTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestTypeAttr getAddedCustRequestTypeAttr() {
		return addedCustRequestTypeAttr;
	}

}
