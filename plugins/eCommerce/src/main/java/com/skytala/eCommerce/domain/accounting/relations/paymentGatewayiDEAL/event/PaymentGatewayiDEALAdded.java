package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayiDEAL.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayiDEAL.model.PaymentGatewayiDEAL;
public class PaymentGatewayiDEALAdded implements Event{

	private PaymentGatewayiDEAL addedPaymentGatewayiDEAL;
	private boolean success;

	public PaymentGatewayiDEALAdded(PaymentGatewayiDEAL addedPaymentGatewayiDEAL, boolean success){
		this.addedPaymentGatewayiDEAL = addedPaymentGatewayiDEAL;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGatewayiDEAL getAddedPaymentGatewayiDEAL() {
		return addedPaymentGatewayiDEAL;
	}

}
