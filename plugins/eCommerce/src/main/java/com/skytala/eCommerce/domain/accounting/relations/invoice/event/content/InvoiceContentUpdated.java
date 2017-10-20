package com.skytala.eCommerce.domain.accounting.relations.invoice.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.content.InvoiceContent;
public class InvoiceContentUpdated implements Event{

	private boolean success;

	public InvoiceContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
