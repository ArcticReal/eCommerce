package com.skytala.eCommerce.domain.accounting.relations.invoiceAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceAttribute.model.InvoiceAttribute;
public class InvoiceAttributeDeleted implements Event{

	private boolean success;

	public InvoiceAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
