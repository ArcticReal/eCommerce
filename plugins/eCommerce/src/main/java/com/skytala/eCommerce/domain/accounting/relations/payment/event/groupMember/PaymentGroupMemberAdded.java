package com.skytala.eCommerce.domain.accounting.relations.payment.event.groupMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupMember.PaymentGroupMember;
public class PaymentGroupMemberAdded implements Event{

	private PaymentGroupMember addedPaymentGroupMember;
	private boolean success;

	public PaymentGroupMemberAdded(PaymentGroupMember addedPaymentGroupMember, boolean success){
		this.addedPaymentGroupMember = addedPaymentGroupMember;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentGroupMember getAddedPaymentGroupMember() {
		return addedPaymentGroupMember;
	}

}
