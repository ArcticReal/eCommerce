package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayiDEAL;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayiDEAL.PaymentGatewayiDEAL;
public class PaymentGatewayiDEALFound implements Event{

	private List<PaymentGatewayiDEAL> paymentGatewayiDEALs;

	public PaymentGatewayiDEALFound(List<PaymentGatewayiDEAL> paymentGatewayiDEALs) {
		this.paymentGatewayiDEALs = paymentGatewayiDEALs;
	}

	public List<PaymentGatewayiDEAL> getPaymentGatewayiDEALs()	{
		return paymentGatewayiDEALs;
	}

}
