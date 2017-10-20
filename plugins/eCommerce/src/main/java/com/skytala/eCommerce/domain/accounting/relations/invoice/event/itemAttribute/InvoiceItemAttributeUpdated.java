package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAttribute.InvoiceItemAttribute;
public class InvoiceItemAttributeUpdated implements Event{

	private boolean success;

	public InvoiceItemAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
