package com.skytala.eCommerce.domain.accounting.relations.acctgTransTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransTypeAttr.model.AcctgTransTypeAttr;
public class AcctgTransTypeAttrAdded implements Event{

	private AcctgTransTypeAttr addedAcctgTransTypeAttr;
	private boolean success;

	public AcctgTransTypeAttrAdded(AcctgTransTypeAttr addedAcctgTransTypeAttr, boolean success){
		this.addedAcctgTransTypeAttr = addedAcctgTransTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AcctgTransTypeAttr getAddedAcctgTransTypeAttr() {
		return addedAcctgTransTypeAttr;
	}

}
