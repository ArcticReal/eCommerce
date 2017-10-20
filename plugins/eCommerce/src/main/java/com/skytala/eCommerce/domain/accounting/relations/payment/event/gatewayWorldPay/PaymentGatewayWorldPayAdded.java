package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayWorldPay;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayWorldPay.PaymentGatewayWorldPay;
public class PaymentGatewayWorldPayAdded implements Event{

	private PaymentGatewayWorldPay addedPaymentGatewayWorldPay;
	private boolean success;

	public PaymentGatewayWorldPayAdded(PaymentGatewayWorldPay addedPaymentGatewayWorldPay, boolean success){
		this.addedPaymentGatewayWorldPay = addedPaymentGatewayWorldPay;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayWorldPay getAddedPaymentGatewayWorldPay() {
		return addedPaymentGatewayWorldPay;
	}

}
