package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayPal;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayPal.PaymentGatewayPayPal;
public class PaymentGatewayPayPalAdded implements Event{

	private PaymentGatewayPayPal addedPaymentGatewayPayPal;
	private boolean success;

	public PaymentGatewayPayPalAdded(PaymentGatewayPayPal addedPaymentGatewayPayPal, boolean success){
		this.addedPaymentGatewayPayPal = addedPaymentGatewayPayPal;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayPayPal getAddedPaymentGatewayPayPal() {
		return addedPaymentGatewayPayPal;
	}

}
