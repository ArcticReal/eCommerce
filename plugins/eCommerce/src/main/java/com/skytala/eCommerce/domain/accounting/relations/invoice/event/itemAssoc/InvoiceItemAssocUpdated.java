package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssoc.InvoiceItemAssoc;
public class InvoiceItemAssocUpdated implements Event{

	private boolean success;

	public InvoiceItemAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
