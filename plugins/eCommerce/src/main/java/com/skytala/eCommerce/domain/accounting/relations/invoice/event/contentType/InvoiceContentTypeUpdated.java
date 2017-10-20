package com.skytala.eCommerce.domain.accounting.relations.invoice.event.contentType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contentType.InvoiceContentType;
public class InvoiceContentTypeUpdated implements Event{

	private boolean success;

	public InvoiceContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
