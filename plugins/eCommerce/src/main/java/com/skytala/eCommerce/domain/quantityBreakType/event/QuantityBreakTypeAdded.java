package com.skytala.eCommerce.domain.quantityBreakType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.quantityBreakType.model.QuantityBreakType;
public class QuantityBreakTypeAdded implements Event{

	private QuantityBreakType addedQuantityBreakType;
	private boolean success;

	public QuantityBreakTypeAdded(QuantityBreakType addedQuantityBreakType, boolean success){
		this.addedQuantityBreakType = addedQuantityBreakType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuantityBreakType getAddedQuantityBreakType() {
		return addedQuantityBreakType;
	}

}
