package com.skytala.eCommerce.domain.paymentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentType.model.PaymentType;
public class PaymentTypeDeleted implements Event{

	private boolean success;

	public PaymentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
