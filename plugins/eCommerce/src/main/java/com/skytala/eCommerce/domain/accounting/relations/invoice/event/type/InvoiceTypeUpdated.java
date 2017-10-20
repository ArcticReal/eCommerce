package com.skytala.eCommerce.domain.accounting.relations.invoice.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.type.InvoiceType;
public class InvoiceTypeUpdated implements Event{

	private boolean success;

	public InvoiceTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}