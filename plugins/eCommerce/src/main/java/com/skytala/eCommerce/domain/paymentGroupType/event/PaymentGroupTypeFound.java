package com.skytala.eCommerce.domain.paymentGroupType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentGroupType.model.PaymentGroupType;
public class PaymentGroupTypeFound implements Event{

	private List<PaymentGroupType> paymentGroupTypes;

	public PaymentGroupTypeFound(List<PaymentGroupType> paymentGroupTypes) {
		this.paymentGroupTypes = paymentGroupTypes;
	}

	public List<PaymentGroupType> getPaymentGroupTypes()	{
		return paymentGroupTypes;
	}

}
