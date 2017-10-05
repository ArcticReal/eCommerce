package com.skytala.eCommerce.domain.paymentContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentContentType.model.PaymentContentType;
public class PaymentContentTypeDeleted implements Event{

	private boolean success;

	public PaymentContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
