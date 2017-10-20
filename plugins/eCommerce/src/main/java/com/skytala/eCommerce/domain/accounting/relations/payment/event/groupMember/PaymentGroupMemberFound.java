package com.skytala.eCommerce.domain.accounting.relations.payment.event.groupMember;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupMember.PaymentGroupMember;
public class PaymentGroupMemberFound implements Event{

	private List<PaymentGroupMember> paymentGroupMembers;

	public PaymentGroupMemberFound(List<PaymentGroupMember> paymentGroupMembers) {
		this.paymentGroupMembers = paymentGroupMembers;
	}

	public List<PaymentGroupMember> getPaymentGroupMembers()	{
		return paymentGroupMembers;
	}

}
