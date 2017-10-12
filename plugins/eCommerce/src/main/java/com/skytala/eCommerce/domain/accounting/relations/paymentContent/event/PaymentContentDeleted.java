package com.skytala.eCommerce.domain.accounting.relations.paymentContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentContent.model.PaymentContent;
public class PaymentContentDeleted implements Event{

	private boolean success;

	public PaymentContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
