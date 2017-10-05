package com.skytala.eCommerce.domain.paymentGatewayConfig.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentGatewayConfig.model.PaymentGatewayConfig;
public class PaymentGatewayConfigFound implements Event{

	private List<PaymentGatewayConfig> paymentGatewayConfigs;

	public PaymentGatewayConfigFound(List<PaymentGatewayConfig> paymentGatewayConfigs) {
		this.paymentGatewayConfigs = paymentGatewayConfigs;
	}

	public List<PaymentGatewayConfig> getPaymentGatewayConfigs()	{
		return paymentGatewayConfigs;
	}

}
