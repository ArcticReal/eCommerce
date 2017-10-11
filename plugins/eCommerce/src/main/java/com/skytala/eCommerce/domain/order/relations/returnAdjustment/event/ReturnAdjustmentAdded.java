package com.skytala.eCommerce.domain.order.relations.returnAdjustment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.ReturnAdjustment;
public class ReturnAdjustmentAdded implements Event{

	private ReturnAdjustment addedReturnAdjustment;
	private boolean success;

	public ReturnAdjustmentAdded(ReturnAdjustment addedReturnAdjustment, boolean success){
		this.addedReturnAdjustment = addedReturnAdjustment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnAdjustment getAddedReturnAdjustment() {
		return addedReturnAdjustment;
	}

}
