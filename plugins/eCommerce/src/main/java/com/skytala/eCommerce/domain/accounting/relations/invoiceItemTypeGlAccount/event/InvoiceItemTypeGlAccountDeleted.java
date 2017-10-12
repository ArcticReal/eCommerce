package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeGlAccount.model.InvoiceItemTypeGlAccount;
public class InvoiceItemTypeGlAccountDeleted implements Event{

	private boolean success;

	public InvoiceItemTypeGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
