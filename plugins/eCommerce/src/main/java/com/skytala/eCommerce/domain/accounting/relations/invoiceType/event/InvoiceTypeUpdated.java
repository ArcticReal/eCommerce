package com.skytala.eCommerce.domain.accounting.relations.invoiceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceType.model.InvoiceType;
public class InvoiceTypeUpdated implements Event{

	private boolean success;

	public InvoiceTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
