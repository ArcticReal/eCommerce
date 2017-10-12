package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.model.FinAccountTypeAttr;
public class FinAccountTypeAttrDeleted implements Event{

	private boolean success;

	public FinAccountTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
