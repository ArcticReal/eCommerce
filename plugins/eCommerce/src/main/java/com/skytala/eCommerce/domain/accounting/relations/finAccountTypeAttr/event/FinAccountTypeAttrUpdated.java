package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.model.FinAccountTypeAttr;
public class FinAccountTypeAttrUpdated implements Event{

	private boolean success;

	public FinAccountTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
