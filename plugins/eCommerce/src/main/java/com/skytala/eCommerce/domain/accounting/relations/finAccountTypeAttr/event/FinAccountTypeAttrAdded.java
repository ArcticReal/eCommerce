package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.model.FinAccountTypeAttr;
public class FinAccountTypeAttrAdded implements Event{

	private FinAccountTypeAttr addedFinAccountTypeAttr;
	private boolean success;

	public FinAccountTypeAttrAdded(FinAccountTypeAttr addedFinAccountTypeAttr, boolean success){
		this.addedFinAccountTypeAttr = addedFinAccountTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccountTypeAttr getAddedFinAccountTypeAttr() {
		return addedFinAccountTypeAttr;
	}

}
