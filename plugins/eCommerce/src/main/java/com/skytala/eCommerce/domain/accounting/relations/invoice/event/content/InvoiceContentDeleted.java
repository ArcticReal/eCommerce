package com.skytala.eCommerce.domain.accounting.relations.invoice.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.content.InvoiceContent;
public class InvoiceContentDeleted implements Event{

	private boolean success;

	public InvoiceContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
