package com.skytala.eCommerce.domain.accounting.relations.payment.event.groupType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupType.PaymentGroupType;
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
