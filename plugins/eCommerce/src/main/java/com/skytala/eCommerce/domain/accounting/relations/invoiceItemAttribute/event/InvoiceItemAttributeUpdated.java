package com.skytala.eCommerce.domain.accounting.relations.invoiceItemAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAttribute.model.InvoiceItemAttribute;
public class InvoiceItemAttributeUpdated implements Event{

	private boolean success;

	public InvoiceItemAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
