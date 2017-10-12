package com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.model.PayrollPreference;
public class PayrollPreferenceFound implements Event{

	private List<PayrollPreference> payrollPreferences;

	public PayrollPreferenceFound(List<PayrollPreference> payrollPreferences) {
		this.payrollPreferences = payrollPreferences;
	}

	public List<PayrollPreference> getPayrollPreferences()	{
		return payrollPreferences;
	}

}
