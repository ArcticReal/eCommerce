package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.typeAttr.FinAccountTypeAttr;
public class FinAccountTypeAttrUpdated implements Event{

	private boolean success;

	public FinAccountTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
