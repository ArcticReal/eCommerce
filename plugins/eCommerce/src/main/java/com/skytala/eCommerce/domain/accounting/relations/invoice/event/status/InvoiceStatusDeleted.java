package com.skytala.eCommerce.domain.accounting.relations.invoice.event.status;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.status.InvoiceStatus;
public class InvoiceStatusDeleted implements Event{

	private boolean success;

	public InvoiceStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
