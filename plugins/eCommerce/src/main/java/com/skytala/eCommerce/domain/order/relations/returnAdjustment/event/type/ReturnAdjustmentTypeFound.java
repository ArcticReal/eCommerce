package com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.type.ReturnAdjustmentType;
public class ReturnAdjustmentTypeFound implements Event{

	private List<ReturnAdjustmentType> returnAdjustmentTypes;

	public ReturnAdjustmentTypeFound(List<ReturnAdjustmentType> returnAdjustmentTypes) {
		this.returnAdjustmentTypes = returnAdjustmentTypes;
	}

	public List<ReturnAdjustmentType> getReturnAdjustmentTypes()	{
		return returnAdjustmentTypes;
	}

}
