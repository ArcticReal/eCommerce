package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.attribute.AcctgTransAttribute;
public class AcctgTransAttributeDeleted implements Event{

	private boolean success;

	public AcctgTransAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
