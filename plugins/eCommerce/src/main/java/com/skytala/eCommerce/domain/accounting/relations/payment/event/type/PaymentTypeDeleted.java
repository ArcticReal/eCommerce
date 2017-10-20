package com.skytala.eCommerce.domain.accounting.relations.payment.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.type.PaymentType;
public class PaymentTypeDeleted implements Event{

	private boolean success;

	public PaymentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
