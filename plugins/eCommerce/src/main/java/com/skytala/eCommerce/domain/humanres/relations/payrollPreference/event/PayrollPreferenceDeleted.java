package com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.model.PayrollPreference;
public class PayrollPreferenceDeleted implements Event{

	private boolean success;

	public PayrollPreferenceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
