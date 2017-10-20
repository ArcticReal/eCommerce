package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transTypeAttr.FinAccountTransTypeAttr;
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
