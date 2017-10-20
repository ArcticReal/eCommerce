package com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.type.ReturnAdjustmentType;
public class ReturnAdjustmentTypeAdded implements Event{

	private ReturnAdjustmentType addedReturnAdjustmentType;
	private boolean success;

	public ReturnAdjustmentTypeAdded(ReturnAdjustmentType addedReturnAdjustmentType, boolean success){
		this.addedReturnAdjustmentType = addedReturnAdjustmentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnAdjustmentType getAddedReturnAdjustmentType() {
		return addedReturnAdjustmentType;
	}

}
