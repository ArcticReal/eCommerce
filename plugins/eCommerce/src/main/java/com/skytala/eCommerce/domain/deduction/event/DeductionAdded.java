package com.skytala.eCommerce.domain.deduction.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.deduction.model.Deduction;
public class DeductionAdded implements Event{

	private Deduction addedDeduction;
	private boolean success;

	public DeductionAdded(Deduction addedDeduction, boolean success){
		this.addedDeduction = addedDeduction;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Deduction getAddedDeduction() {
		return addedDeduction;
	}

}
