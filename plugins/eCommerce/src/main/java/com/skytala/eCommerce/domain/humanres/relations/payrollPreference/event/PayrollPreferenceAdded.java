package com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.model.PayrollPreference;
public class PayrollPreferenceAdded implements Event{

	private PayrollPreference addedPayrollPreference;
	private boolean success;

	public PayrollPreferenceAdded(PayrollPreference addedPayrollPreference, boolean success){
		this.addedPayrollPreference = addedPayrollPreference;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PayrollPreference getAddedPayrollPreference() {
		return addedPayrollPreference;
	}

}
