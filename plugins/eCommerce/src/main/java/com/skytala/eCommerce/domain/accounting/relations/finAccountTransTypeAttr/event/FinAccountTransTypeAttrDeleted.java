package com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.model.FinAccountTransTypeAttr;
public class FinAccountTransTypeAttrDeleted implements Event{

	private boolean success;

	public FinAccountTransTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
