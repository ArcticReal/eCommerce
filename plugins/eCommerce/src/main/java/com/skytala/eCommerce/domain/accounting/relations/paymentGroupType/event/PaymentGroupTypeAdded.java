package com.skytala.eCommerce.domain.accounting.relations.paymentGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGroupType.model.PaymentGroupType;
public class PaymentGroupTypeAdded implements Event{

	private PaymentGroupType addedPaymentGroupType;
	private boolean success;

	public PaymentGroupTypeAdded(PaymentGroupType addedPaymentGroupType, boolean success){
		this.addedPaymentGroupType = addedPaymentGroupType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGroupType getAddedPaymentGroupType() {
		return addedPaymentGroupType;
	}

}
