package com.skytala.eCommerce.domain.accounting.relations.paymentGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGroup.model.PaymentGroup;
public class PaymentGroupDeleted implements Event{

	private boolean success;

	public PaymentGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
