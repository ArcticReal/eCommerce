package com.skytala.eCommerce.domain.accounting.relations.paymentGroupMember.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGroupMember.model.PaymentGroupMember;
public class PaymentGroupMemberUpdated implements Event{

	private boolean success;

	public PaymentGroupMemberUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
