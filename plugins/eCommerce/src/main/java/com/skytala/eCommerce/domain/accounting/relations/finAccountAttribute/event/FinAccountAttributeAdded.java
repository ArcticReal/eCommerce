package com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.model.FinAccountAttribute;
public class FinAccountAttributeAdded implements Event{

	private FinAccountAttribute addedFinAccountAttribute;
	private boolean success;

	public FinAccountAttributeAdded(FinAccountAttribute addedFinAccountAttribute, boolean success){
		this.addedFinAccountAttribute = addedFinAccountAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FinAccountAttribute getAddedFinAccountAttribute() {
		return addedFinAccountAttribute;
	}

}
