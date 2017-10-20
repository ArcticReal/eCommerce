package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeGlAccount;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeGlAccount.InvoiceItemTypeGlAccount;
public class InvoiceItemTypeGlAccountUpdated implements Event{

	private boolean success;

	public InvoiceItemTypeGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
