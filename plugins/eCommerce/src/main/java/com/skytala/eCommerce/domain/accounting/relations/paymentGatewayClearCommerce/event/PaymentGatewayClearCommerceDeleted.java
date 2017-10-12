package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.model.PaymentGatewayClearCommerce;
public class PaymentGatewayClearCommerceDeleted implements Event{

	private boolean success;

	public PaymentGatewayClearCommerceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
