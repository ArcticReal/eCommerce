package com.skytala.eCommerce.domain.varianceReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.varianceReason.model.VarianceReason;
public class VarianceReasonAdded implements Event{

	private VarianceReason addedVarianceReason;
	private boolean success;

	public VarianceReasonAdded(VarianceReason addedVarianceReason, boolean success){
		this.addedVarianceReason = addedVarianceReason;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public VarianceReason getAddedVarianceReason() {
		return addedVarianceReason;
	}

}
