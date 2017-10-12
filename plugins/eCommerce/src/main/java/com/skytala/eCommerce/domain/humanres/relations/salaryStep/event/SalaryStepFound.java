package com.skytala.eCommerce.domain.humanres.relations.salaryStep.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.salaryStep.model.SalaryStep;
public class SalaryStepFound implements Event{

	private List<SalaryStep> salarySteps;

	public SalaryStepFound(List<SalaryStep> salarySteps) {
		this.salarySteps = salarySteps;
	}

	public List<SalaryStep> getSalarySteps()	{
		return salarySteps;
	}

}
