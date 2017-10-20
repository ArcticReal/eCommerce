package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfig;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayConfig.PaymentGatewayConfig;
public class PaymentGatewayConfigAdded implements Event{

	private PaymentGatewayConfig addedPaymentGatewayConfig;
	private boolean success;

	public PaymentGatewayConfigAdded(PaymentGatewayConfig addedPaymentGatewayConfig, boolean success){
		this.addedPaymentGatewayConfig = addedPaymentGatewayConfig;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayConfig getAddedPaymentGatewayConfig() {
		return addedPaymentGatewayConfig;
	}

}
