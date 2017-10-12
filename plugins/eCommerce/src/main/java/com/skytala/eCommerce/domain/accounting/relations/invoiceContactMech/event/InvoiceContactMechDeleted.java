package com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.model.InvoiceContactMech;
public class InvoiceContactMechDeleted implements Event{

	private boolean success;

	public InvoiceContactMechDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
