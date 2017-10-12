package com.skytala.eCommerce.domain.humanres.relations.salaryStep.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.salaryStep.model.SalaryStep;
public class SalaryStepAdded implements Event{

	private SalaryStep addedSalaryStep;
	private boolean success;

	public SalaryStepAdded(SalaryStep addedSalaryStep, boolean success){
		this.addedSalaryStep = addedSalaryStep;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalaryStep getAddedSalaryStep() {
		return addedSalaryStep;
	}

}
