package com.skytala.eCommerce.domain.accounting.relations.paymentContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentContentType.model.PaymentContentType;
public class PaymentContentTypeUpdated implements Event{

	private boolean success;

	public PaymentContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
