package com.skytala.eCommerce.domain.accounting.relations.paymentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentType.model.PaymentType;
public class PaymentTypeUpdated implements Event{

	private boolean success;

	public PaymentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
