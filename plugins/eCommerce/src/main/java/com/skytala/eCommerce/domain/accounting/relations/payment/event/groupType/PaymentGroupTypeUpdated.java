package com.skytala.eCommerce.domain.accounting.relations.payment.event.groupType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupType.PaymentGroupType;
public class PaymentGroupTypeUpdated implements Event{

	private boolean success;

	public PaymentGroupTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}