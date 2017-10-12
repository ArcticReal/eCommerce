package com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssocType.model.InvoiceItemAssocType;
public class InvoiceItemAssocTypeUpdated implements Event{

	private boolean success;

	public InvoiceItemAssocTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
