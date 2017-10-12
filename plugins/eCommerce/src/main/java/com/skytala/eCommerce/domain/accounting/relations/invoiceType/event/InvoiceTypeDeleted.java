package com.skytala.eCommerce.domain.accounting.relations.invoiceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceType.model.InvoiceType;
public class InvoiceTypeDeleted implements Event{

	private boolean success;

	public InvoiceTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
