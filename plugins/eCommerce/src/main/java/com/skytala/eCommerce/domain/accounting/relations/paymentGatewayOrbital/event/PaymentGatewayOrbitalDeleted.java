package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.model.PaymentGatewayOrbital;
public class PaymentGatewayOrbitalDeleted implements Event{

	private boolean success;

	public PaymentGatewayOrbitalDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
