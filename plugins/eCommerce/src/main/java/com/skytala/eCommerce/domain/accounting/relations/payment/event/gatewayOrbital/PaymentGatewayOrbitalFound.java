package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayOrbital;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayOrbital.PaymentGatewayOrbital;
public class PaymentGatewayOrbitalFound implements Event{

	private List<PaymentGatewayOrbital> paymentGatewayOrbitals;

	public PaymentGatewayOrbitalFound(List<PaymentGatewayOrbital> paymentGatewayOrbitals) {
		this.paymentGatewayOrbitals = paymentGatewayOrbitals;
	}

	public List<PaymentGatewayOrbital> getPaymentGatewayOrbitals()	{
		return paymentGatewayOrbitals;
	}

}
