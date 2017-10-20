package com.skytala.eCommerce.domain.order.relations.custRequest.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.attribute.CustRequestAttribute;
public class CustRequestAttributeAdded implements Event{

	private CustRequestAttribute addedCustRequestAttribute;
	private boolean success;

	public CustRequestAttributeAdded(CustRequestAttribute addedCustRequestAttribute, boolean success){
		this.addedCustRequestAttribute = addedCustRequestAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestAttribute getAddedCustRequestAttribute() {
		return addedCustRequestAttribute;
	}

}
