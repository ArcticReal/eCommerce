package com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.attribute.InvoiceAttribute;
public class InvoiceAttributeDeleted implements Event{

	private boolean success;

	public InvoiceAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
