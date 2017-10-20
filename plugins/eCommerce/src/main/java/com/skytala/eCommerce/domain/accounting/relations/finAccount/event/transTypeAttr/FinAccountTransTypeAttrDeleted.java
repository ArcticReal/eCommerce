package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transTypeAttr.FinAccountTransTypeAttr;
public class FinAccountTransTypeAttrDeleted implements Event{

	private boolean success;

	public FinAccountTransTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
