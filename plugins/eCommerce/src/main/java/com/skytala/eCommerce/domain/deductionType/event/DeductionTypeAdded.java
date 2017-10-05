package com.skytala.eCommerce.domain.deductionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.deductionType.model.DeductionType;
public class DeductionTypeAdded implements Event{

	private DeductionType addedDeductionType;
	private boolean success;

	public DeductionTypeAdded(DeductionType addedDeductionType, boolean success){
		this.addedDeductionType = addedDeductionType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DeductionType getAddedDeductionType() {
		return addedDeductionType;
	}

}
