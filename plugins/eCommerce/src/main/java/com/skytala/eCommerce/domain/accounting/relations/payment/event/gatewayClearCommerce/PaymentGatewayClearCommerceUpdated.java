package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayClearCommerce;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayClearCommerce.PaymentGatewayClearCommerce;
public class PaymentGatewayClearCommerceUpdated implements Event{

	private boolean success;

	public PaymentGatewayClearCommerceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
