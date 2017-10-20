package com.skytala.eCommerce.domain.accounting.relations.payment.event.group;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.group.PaymentGroup;
public class PaymentGroupAdded implements Event{

	private PaymentGroup addedPaymentGroup;
	private boolean success;

	public PaymentGroupAdded(PaymentGroup addedPaymentGroup, boolean success){
		this.addedPaymentGroup = addedPaymentGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGroup getAddedPaymentGroup() {
		return addedPaymentGroup;
	}

}
