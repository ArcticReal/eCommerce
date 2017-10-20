package com.skytala.eCommerce.domain.accounting.relations.payment.event.group;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.group.PaymentGroup;
public class PaymentGroupDeleted implements Event{

	private boolean success;

	public PaymentGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
