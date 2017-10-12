package com.skytala.eCommerce.domain.accounting.relations.paymentMethodTypeGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentMethodTypeGlAccount.model.PaymentMethodTypeGlAccount;
public class PaymentMethodTypeGlAccountDeleted implements Event{

	private boolean success;

	public PaymentMethodTypeGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
