package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.model.PaymentGatewayPayflowPro;
public class PaymentGatewayPayflowProAdded implements Event{

	private PaymentGatewayPayflowPro addedPaymentGatewayPayflowPro;
	private boolean success;

	public PaymentGatewayPayflowProAdded(PaymentGatewayPayflowPro addedPaymentGatewayPayflowPro, boolean success){
		this.addedPaymentGatewayPayflowPro = addedPaymentGatewayPayflowPro;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayPayflowPro getAddedPaymentGatewayPayflowPro() {
		return addedPaymentGatewayPayflowPro;
	}

}
