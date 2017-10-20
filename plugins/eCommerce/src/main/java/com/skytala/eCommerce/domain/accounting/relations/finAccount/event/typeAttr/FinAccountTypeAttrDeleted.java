package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.typeAttr.FinAccountTypeAttr;
public class FinAccountTypeAttrDeleted implements Event{

	private boolean success;

	public FinAccountTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
