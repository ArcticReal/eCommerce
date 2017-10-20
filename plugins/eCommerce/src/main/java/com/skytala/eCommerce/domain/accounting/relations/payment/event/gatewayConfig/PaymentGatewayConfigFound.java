package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayConfig;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayConfig.PaymentGatewayConfig;
public class PaymentGatewayConfigFound implements Event{

	private List<PaymentGatewayConfig> paymentGatewayConfigs;

	public PaymentGatewayConfigFound(List<PaymentGatewayConfig> paymentGatewayConfigs) {
		this.paymentGatewayConfigs = paymentGatewayConfigs;
	}

	public List<PaymentGatewayConfig> getPaymentGatewayConfigs()	{
		return paymentGatewayConfigs;
	}

}
