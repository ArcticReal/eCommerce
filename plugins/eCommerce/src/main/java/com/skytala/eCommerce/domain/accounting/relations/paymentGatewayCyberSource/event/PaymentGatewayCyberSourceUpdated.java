package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayCyberSource.model.PaymentGatewayCyberSource;
public class PaymentGatewayCyberSourceUpdated implements Event{

	private boolean success;

	public PaymentGatewayCyberSourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
