package com.skytala.eCommerce.domain.humanres.relations.salaryStep.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.salaryStep.model.SalaryStep;
public class SalaryStepDeleted implements Event{

	private boolean success;

	public SalaryStepDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
