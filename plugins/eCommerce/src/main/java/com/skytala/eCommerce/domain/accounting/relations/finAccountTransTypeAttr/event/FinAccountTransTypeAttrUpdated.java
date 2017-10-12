package com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.model.FinAccountTransTypeAttr;
public class FinAccountTransTypeAttrUpdated implements Event{

	private boolean success;

	public FinAccountTransTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
