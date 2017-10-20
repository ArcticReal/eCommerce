package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAttribute.InvoiceItemAttribute;
public class InvoiceItemAttributeDeleted implements Event{

	private boolean success;

	public InvoiceItemAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
