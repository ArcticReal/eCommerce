package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transAttribute.FinAccountTransAttribute;
public class FinAccountTransAttributeAdded implements Event{

	private FinAccountTransAttribute addedFinAccountTransAttribute;
	private boolean success;

	public FinAccountTransAttributeAdded(FinAccountTransAttribute addedFinAccountTransAttribute, boolean success){
		this.addedFinAccountTransAttribute = addedFinAccountTransAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccountTransAttribute getAddedFinAccountTransAttribute() {
		return addedFinAccountTransAttribute;
	}

}
