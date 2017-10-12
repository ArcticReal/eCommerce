package com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.model.InvoiceTermAttribute;
public class InvoiceTermAttributeUpdated implements Event{

	private boolean success;

	public InvoiceTermAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
