package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayConfigType.model.PaymentGatewayConfigType;
public class PaymentGatewayConfigTypeFound implements Event{

	private List<PaymentGatewayConfigType> paymentGatewayConfigTypes;

	public PaymentGatewayConfigTypeFound(List<PaymentGatewayConfigType> paymentGatewayConfigTypes) {
		this.paymentGatewayConfigTypes = paymentGatewayConfigTypes;
	}

	public List<PaymentGatewayConfigType> getPaymentGatewayConfigTypes()	{
		return paymentGatewayConfigTypes;
	}

}
