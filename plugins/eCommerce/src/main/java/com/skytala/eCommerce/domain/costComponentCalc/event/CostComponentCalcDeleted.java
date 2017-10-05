package com.skytala.eCommerce.domain.costComponentCalc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.costComponentCalc.model.CostComponentCalc;
public class CostComponentCalcDeleted implements Event{

	private boolean success;

	public CostComponentCalcDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
