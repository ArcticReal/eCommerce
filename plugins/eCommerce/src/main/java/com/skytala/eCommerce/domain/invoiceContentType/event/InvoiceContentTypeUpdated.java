package com.skytala.eCommerce.domain.invoiceContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.invoiceContentType.model.InvoiceContentType;
public class InvoiceContentTypeUpdated implements Event{

	private boolean success;

	public InvoiceContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
