package com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.model.InvoiceItemAssoc;
public class InvoiceItemAssocDeleted implements Event{

	private boolean success;

	public InvoiceItemAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
