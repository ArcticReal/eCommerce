package com.skytala.eCommerce.domain.accounting.relations.acctgTransTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransTypeAttr.model.AcctgTransTypeAttr;
public class AcctgTransTypeAttrDeleted implements Event{

	private boolean success;

	public AcctgTransTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
