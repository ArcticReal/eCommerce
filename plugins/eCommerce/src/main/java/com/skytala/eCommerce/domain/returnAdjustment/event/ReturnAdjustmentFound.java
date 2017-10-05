package com.skytala.eCommerce.domain.returnAdjustment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnAdjustment.model.ReturnAdjustment;
public class ReturnAdjustmentFound implements Event{

	private List<ReturnAdjustment> returnAdjustments;

	public ReturnAdjustmentFound(List<ReturnAdjustment> returnAdjustments) {
		this.returnAdjustments = returnAdjustments;
	}

	public List<ReturnAdjustment> getReturnAdjustments()	{
		return returnAdjustments;
	}

}
