package com.skytala.eCommerce.domain.accounting.relations.payment.event.payPalMethod;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.payPalMethod.PayPalPaymentMethod;
public class PayPalPaymentMethodAdded implements Event{

	private PayPalPaymentMethod addedPayPalPaymentMethod;
	private boolean success;

	public PayPalPaymentMethodAdded(PayPalPaymentMethod addedPayPalPaymentMethod, boolean success){
		this.addedPayPalPaymentMethod = addedPayPalPaymentMethod;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PayPalPaymentMethod getAddedPayPalPaymentMethod() {
		return addedPayPalPaymentMethod;
	}

}
