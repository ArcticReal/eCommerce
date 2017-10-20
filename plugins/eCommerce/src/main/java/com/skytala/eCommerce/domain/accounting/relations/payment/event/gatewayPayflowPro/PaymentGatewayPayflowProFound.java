package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayflowPro;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayflowPro.PaymentGatewayPayflowPro;
public class PaymentGatewayPayflowProFound implements Event{

	private List<PaymentGatewayPayflowPro> paymentGatewayPayflowPros;

	public PaymentGatewayPayflowProFound(List<PaymentGatewayPayflowPro> paymentGatewayPayflowPros) {
		this.paymentGatewayPayflowPros = paymentGatewayPayflowPros;
	}

	public List<PaymentGatewayPayflowPro> getPaymentGatewayPayflowPros()	{
		return paymentGatewayPayflowPros;
	}

}
