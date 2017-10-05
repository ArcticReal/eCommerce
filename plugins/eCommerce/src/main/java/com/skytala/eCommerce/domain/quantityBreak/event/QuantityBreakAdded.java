package com.skytala.eCommerce.domain.quantityBreak.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.quantityBreak.model.QuantityBreak;
public class QuantityBreakAdded implements Event{

	private QuantityBreak addedQuantityBreak;
	private boolean success;

	public QuantityBreakAdded(QuantityBreak addedQuantityBreak, boolean success){
		this.addedQuantityBreak = addedQuantityBreak;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuantityBreak getAddedQuantityBreak() {
		return addedQuantityBreak;
	}

}
