package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.model.PaymentGatewaySecurePay;
public class PaymentGatewaySecurePayAdded implements Event{

	private PaymentGatewaySecurePay addedPaymentGatewaySecurePay;
	private boolean success;

	public PaymentGatewaySecurePayAdded(PaymentGatewaySecurePay addedPaymentGatewaySecurePay, boolean success){
		this.addedPaymentGatewaySecurePay = addedPaymentGatewaySecurePay;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewaySecurePay getAddedPaymentGatewaySecurePay() {
		return addedPaymentGatewaySecurePay;
	}

}
