package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayOrbital;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayOrbital.PaymentGatewayOrbital;
public class PaymentGatewayOrbitalAdded implements Event{

	private PaymentGatewayOrbital addedPaymentGatewayOrbital;
	private boolean success;

	public PaymentGatewayOrbitalAdded(PaymentGatewayOrbital addedPaymentGatewayOrbital, boolean success){
		this.addedPaymentGatewayOrbital = addedPaymentGatewayOrbital;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayOrbital getAddedPaymentGatewayOrbital() {
		return addedPaymentGatewayOrbital;
	}

}
