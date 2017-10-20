package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeGlAccount;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeGlAccount.InvoiceItemTypeGlAccount;
public class InvoiceItemTypeGlAccountDeleted implements Event{

	private boolean success;

	public InvoiceItemTypeGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
