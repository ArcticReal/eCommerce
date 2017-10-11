package com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestTypeAttr.model.CustRequestTypeAttr;
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
