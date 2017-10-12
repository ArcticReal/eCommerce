package com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceStatus.model.InvoiceStatus;
public class InvoiceStatusDeleted implements Event{

	private boolean success;

	public InvoiceStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
