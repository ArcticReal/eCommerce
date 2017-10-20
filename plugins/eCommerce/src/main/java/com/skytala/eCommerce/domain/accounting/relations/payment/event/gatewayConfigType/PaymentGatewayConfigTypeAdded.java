package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfigType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayConfigType.PaymentGatewayConfigType;
public class PaymentGatewayConfigTypeAdded implements Event{

	private PaymentGatewayConfigType addedPaymentGatewayConfigType;
	private boolean success;

	public PaymentGatewayConfigTypeAdded(PaymentGatewayConfigType addedPaymentGatewayConfigType, boolean success){
		this.addedPaymentGatewayConfigType = addedPaymentGatewayConfigType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayConfigType getAddedPaymentGatewayConfigType() {
		return addedPaymentGatewayConfigType;
	}

}
