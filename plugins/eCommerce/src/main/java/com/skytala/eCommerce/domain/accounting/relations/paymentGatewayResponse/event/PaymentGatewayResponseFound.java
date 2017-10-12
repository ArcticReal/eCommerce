package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.model.PaymentGatewayResponse;
public class PaymentGatewayResponseFound implements Event{

	private List<PaymentGatewayResponse> paymentGatewayResponses;

	public PaymentGatewayResponseFound(List<PaymentGatewayResponse> paymentGatewayResponses) {
		this.paymentGatewayResponses = paymentGatewayResponses;
	}

	public List<PaymentGatewayResponse> getPaymentGatewayResponses()	{
		return paymentGatewayResponses;
	}

}
