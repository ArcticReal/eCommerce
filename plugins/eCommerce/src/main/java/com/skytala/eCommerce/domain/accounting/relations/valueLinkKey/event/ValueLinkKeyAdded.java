package com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.model.ValueLinkKey;
public class ValueLinkKeyAdded implements Event{

	private ValueLinkKey addedValueLinkKey;
	private boolean success;

	public ValueLinkKeyAdded(ValueLinkKey addedValueLinkKey, boolean success){
		this.addedValueLinkKey = addedValueLinkKey;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ValueLinkKey getAddedValueLinkKey() {
		return addedValueLinkKey;
	}

}
