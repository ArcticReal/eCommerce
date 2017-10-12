package com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.model.InvoiceTermAttribute;
public class InvoiceTermAttributeDeleted implements Event{

	private boolean success;

	public InvoiceTermAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
