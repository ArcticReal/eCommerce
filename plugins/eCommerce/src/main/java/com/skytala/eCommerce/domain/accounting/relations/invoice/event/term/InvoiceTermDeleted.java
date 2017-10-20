package com.skytala.eCommerce.domain.accounting.relations.invoice.event.term;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.term.InvoiceTerm;
public class InvoiceTermDeleted implements Event{

	private boolean success;

	public InvoiceTermDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
