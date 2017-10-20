package com.skytala.eCommerce.domain.product.relations.quantityBreak.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.quantityBreak.model.type.QuantityBreakType;
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
