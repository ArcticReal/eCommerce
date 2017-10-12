package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeAttr.model.InvoiceItemTypeAttr;
public class InvoiceItemTypeAttrUpdated implements Event{

	private boolean success;

	public InvoiceItemTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
