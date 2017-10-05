package com.skytala.eCommerce.domain.paymentGatewayResponse.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentGatewayResponse.model.PaymentGatewayResponse;
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
