package com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.model.FinAccountTransTypeAttr;
public class FinAccountTransTypeAttrAdded implements Event{

	private FinAccountTransTypeAttr addedFinAccountTransTypeAttr;
	private boolean success;

	public FinAccountTransTypeAttrAdded(FinAccountTransTypeAttr addedFinAccountTransTypeAttr, boolean success){
		this.addedFinAccountTransTypeAttr = addedFinAccountTransTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccountTransTypeAttr getAddedFinAccountTransTypeAttr() {
		return addedFinAccountTransTypeAttr;
	}

}
