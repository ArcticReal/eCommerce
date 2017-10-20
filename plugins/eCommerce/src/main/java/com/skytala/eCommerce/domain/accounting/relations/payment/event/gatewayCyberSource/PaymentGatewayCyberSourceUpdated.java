package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayCyberSource;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayCyberSource.PaymentGatewayCyberSource;
public class PaymentGatewayCyberSourceUpdated implements Event{

	private boolean success;

	public PaymentGatewayCyberSourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
