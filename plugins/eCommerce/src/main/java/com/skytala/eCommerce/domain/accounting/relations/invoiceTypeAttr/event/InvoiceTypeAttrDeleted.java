package com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.model.InvoiceTypeAttr;
public class InvoiceTypeAttrDeleted implements Event{

	private boolean success;

	public InvoiceTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
