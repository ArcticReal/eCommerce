package com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.model.BillingAccountRole;
public class BillingAccountRoleFound implements Event{

	private List<BillingAccountRole> billingAccountRoles;

	public BillingAccountRoleFound(List<BillingAccountRole> billingAccountRoles) {
		this.billingAccountRoles = billingAccountRoles;
	}

	public List<BillingAccountRole> getBillingAccountRoles()	{
		return billingAccountRoles;
	}

}
