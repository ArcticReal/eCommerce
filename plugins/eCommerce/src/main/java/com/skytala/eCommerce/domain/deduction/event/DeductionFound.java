package com.skytala.eCommerce.domain.deduction.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.deduction.model.Deduction;
public class DeductionFound implements Event{

	private List<Deduction> deductions;

	public DeductionFound(List<Deduction> deductions) {
		this.deductions = deductions;
	}

	public List<Deduction> getDeductions()	{
		return deductions;
	}

}
