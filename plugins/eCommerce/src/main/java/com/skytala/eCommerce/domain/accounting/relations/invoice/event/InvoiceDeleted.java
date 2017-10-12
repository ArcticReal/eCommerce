package com.skytala.eCommerce.domain.accounting.relations.invoice.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.Invoice;
public class InvoiceDeleted implements Event{

	private boolean success;

	public InvoiceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
