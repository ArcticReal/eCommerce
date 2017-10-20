package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayResponse;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayResponse.PaymentGatewayResponse;
public class PaymentGatewayResponseAdded implements Event{

	private PaymentGatewayResponse addedPaymentGatewayResponse;
	private boolean success;

	public PaymentGatewayResponseAdded(PaymentGatewayResponse addedPaymentGatewayResponse, boolean success){
		this.addedPaymentGatewayResponse = addedPaymentGatewayResponse;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayResponse getAddedPaymentGatewayResponse() {
		return addedPaymentGatewayResponse;
	}

}
