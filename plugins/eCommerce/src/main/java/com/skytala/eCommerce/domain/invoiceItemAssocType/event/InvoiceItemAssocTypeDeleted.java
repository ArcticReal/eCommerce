package com.skytala.eCommerce.domain.invoiceItemAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.invoiceItemAssocType.model.InvoiceItemAssocType;
public class InvoiceItemAssocTypeDeleted implements Event{

	private boolean success;

	public InvoiceItemAssocTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
