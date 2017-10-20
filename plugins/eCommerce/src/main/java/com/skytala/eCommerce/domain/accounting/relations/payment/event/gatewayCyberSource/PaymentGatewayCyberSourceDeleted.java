package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayCyberSource;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayCyberSource.PaymentGatewayCyberSource;
public class PaymentGatewayCyberSourceDeleted implements Event{

	private boolean success;

	public PaymentGatewayCyberSourceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
