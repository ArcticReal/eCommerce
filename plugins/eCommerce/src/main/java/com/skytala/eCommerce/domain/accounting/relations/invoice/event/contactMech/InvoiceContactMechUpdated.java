package com.skytala.eCommerce.domain.accounting.relations.invoice.event.contactMech;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contactMech.InvoiceContactMech;
public class InvoiceContactMechUpdated implements Event{

	private boolean success;

	public InvoiceContactMechUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}