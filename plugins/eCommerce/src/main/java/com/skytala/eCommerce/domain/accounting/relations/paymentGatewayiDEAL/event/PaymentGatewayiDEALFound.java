package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayiDEAL.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayiDEAL.model.PaymentGatewayiDEAL;
public class PaymentGatewayiDEALFound implements Event{

	private List<PaymentGatewayiDEAL> paymentGatewayiDEALs;

	public PaymentGatewayiDEALFound(List<PaymentGatewayiDEAL> paymentGatewayiDEALs) {
		this.paymentGatewayiDEALs = paymentGatewayiDEALs;
	}

	public List<PaymentGatewayiDEAL> getPaymentGatewayiDEALs()	{
		return paymentGatewayiDEALs;
	}

}
