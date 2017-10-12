package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.model.InvoiceItemTypeAttr;
public class InvoiceItemTypeAttrDeleted implements Event{

	private boolean success;

	public InvoiceItemTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
