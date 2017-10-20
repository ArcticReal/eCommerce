package com.skytala.eCommerce.domain.accounting.relations.payment.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.content.PaymentContent;
public class PaymentContentUpdated implements Event{

	private boolean success;

	public PaymentContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
