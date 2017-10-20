package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentTypeMap;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentTypeMap.PaymentGlAccountTypeMap;
public class PaymentGlAccountTypeMapUpdated implements Event{

	private boolean success;

	public PaymentGlAccountTypeMapUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
