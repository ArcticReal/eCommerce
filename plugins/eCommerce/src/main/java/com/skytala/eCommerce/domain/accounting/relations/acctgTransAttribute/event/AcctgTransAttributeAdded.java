package com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.model.AcctgTransAttribute;
public class AcctgTransAttributeAdded implements Event{

	private AcctgTransAttribute addedAcctgTransAttribute;
	private boolean success;

	public AcctgTransAttributeAdded(AcctgTransAttribute addedAcctgTransAttribute, boolean success){
		this.addedAcctgTransAttribute = addedAcctgTransAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AcctgTransAttribute getAddedAcctgTransAttribute() {
		return addedAcctgTransAttribute;
	}

}
