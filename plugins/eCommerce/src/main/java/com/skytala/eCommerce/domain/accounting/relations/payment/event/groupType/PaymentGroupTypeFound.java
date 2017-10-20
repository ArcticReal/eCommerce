package com.skytala.eCommerce.domain.accounting.relations.payment.event.groupType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupType.PaymentGroupType;
public class PaymentGroupTypeFound implements Event{

	private List<PaymentGroupType> paymentGroupTypes;

	public PaymentGroupTypeFound(List<PaymentGroupType> paymentGroupTypes) {
		this.paymentGroupTypes = paymentGroupTypes;
	}

	public List<PaymentGroupType> getPaymentGroupTypes()	{
		return paymentGroupTypes;
	}

}
