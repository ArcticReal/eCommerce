package com.skytala.eCommerce.domain.accounting.relations.paymentMethodTypeGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentMethodTypeGlAccount.model.PaymentMethodTypeGlAccount;
public class PaymentMethodTypeGlAccountUpdated implements Event{

	private boolean success;

	public PaymentMethodTypeGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
