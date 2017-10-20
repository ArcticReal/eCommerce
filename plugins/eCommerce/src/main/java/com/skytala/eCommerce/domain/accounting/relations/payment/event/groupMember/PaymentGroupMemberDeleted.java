package com.skytala.eCommerce.domain.accounting.relations.payment.event.groupMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupMember.PaymentGroupMember;
public class PaymentGroupMemberDeleted implements Event{

	private boolean success;

	public PaymentGroupMemberDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
