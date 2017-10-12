package com.skytala.eCommerce.domain.accounting.relations.paymentContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentContent.model.PaymentContent;
public class PaymentContentUpdated implements Event{

	private boolean success;

	public PaymentContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
