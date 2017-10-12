package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.model.PaymentGatewayClearCommerce;
public class PaymentGatewayClearCommerceUpdated implements Event{

	private boolean success;

	public PaymentGatewayClearCommerceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
