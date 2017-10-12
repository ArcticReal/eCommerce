package com.skytala.eCommerce.domain.accounting.relations.paymentGlAccountTypeMap.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGlAccountTypeMap.model.PaymentGlAccountTypeMap;
public class PaymentGlAccountTypeMapUpdated implements Event{

	private boolean success;

	public PaymentGlAccountTypeMapUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
