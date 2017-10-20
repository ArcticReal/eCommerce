package com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.termAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.termAttr.BillingAccountTermAttr;
public class BillingAccountTermAttrFound implements Event{

	private List<BillingAccountTermAttr> billingAccountTermAttrs;

	public BillingAccountTermAttrFound(List<BillingAccountTermAttr> billingAccountTermAttrs) {
		this.billingAccountTermAttrs = billingAccountTermAttrs;
	}

	public List<BillingAccountTermAttr> getBillingAccountTermAttrs()	{
		return billingAccountTermAttrs;
	}

}
