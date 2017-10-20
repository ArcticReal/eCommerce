package com.skytala.eCommerce.domain.accounting.relations.payment.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.type.PaymentType;
public class PaymentTypeUpdated implements Event{

	private boolean success;

	public PaymentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
