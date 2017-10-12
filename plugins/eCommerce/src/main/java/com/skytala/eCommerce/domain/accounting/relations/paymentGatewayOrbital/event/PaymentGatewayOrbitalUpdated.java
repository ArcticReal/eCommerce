package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.model.PaymentGatewayOrbital;
public class PaymentGatewayOrbitalUpdated implements Event{

	private boolean success;

	public PaymentGatewayOrbitalUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
