package com.skytala.eCommerce.domain.humanres.relations.salaryStep.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.salaryStep.model.SalaryStep;
public class SalaryStepUpdated implements Event{

	private boolean success;

	public SalaryStepUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
