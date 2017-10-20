package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.typeAttr.AcctgTransTypeAttr;
public class AcctgTransTypeAttrUpdated implements Event{

	private boolean success;

	public AcctgTransTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
