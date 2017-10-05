package com.skytala.eCommerce.domain.invoice.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.invoice.model.Invoice;
public class InvoiceUpdated implements Event{

	private boolean success;

	public InvoiceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
