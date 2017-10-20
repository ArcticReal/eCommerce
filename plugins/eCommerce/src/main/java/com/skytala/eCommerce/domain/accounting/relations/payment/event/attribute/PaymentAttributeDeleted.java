package com.skytala.eCommerce.domain.accounting.relations.payment.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.attribute.PaymentAttribute;
public class PaymentAttributeDeleted implements Event{

	private boolean success;

	public PaymentAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
