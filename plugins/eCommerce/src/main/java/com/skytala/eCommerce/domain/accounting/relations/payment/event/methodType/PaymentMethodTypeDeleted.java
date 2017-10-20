package com.skytala.eCommerce.domain.accounting.relations.payment.event.methodType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.methodType.PaymentMethodType;
public class PaymentMethodTypeDeleted implements Event{

	private boolean success;

	public PaymentMethodTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
