package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayEway;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayEway.PaymentGatewayEway;
public class PaymentGatewayEwayAdded implements Event{

	private PaymentGatewayEway addedPaymentGatewayEway;
	private boolean success;

	public PaymentGatewayEwayAdded(PaymentGatewayEway addedPaymentGatewayEway, boolean success){
		this.addedPaymentGatewayEway = addedPaymentGatewayEway;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayEway getAddedPaymentGatewayEway() {
		return addedPaymentGatewayEway;
	}

}
