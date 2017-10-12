package com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.model.PayrollPreference;
public class PayrollPreferenceUpdated implements Event{

	private boolean success;

	public PayrollPreferenceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
