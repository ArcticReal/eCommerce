package com.skytala.eCommerce.domain.accounting.relations.invoice.event.term;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.term.InvoiceTerm;
public class InvoiceTermUpdated implements Event{

	private boolean success;

	public InvoiceTermUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
