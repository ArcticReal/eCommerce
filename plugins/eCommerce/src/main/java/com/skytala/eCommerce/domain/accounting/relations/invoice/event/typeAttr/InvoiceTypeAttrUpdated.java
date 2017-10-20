package com.skytala.eCommerce.domain.accounting.relations.invoice.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.typeAttr.InvoiceTypeAttr;
public class InvoiceTypeAttrUpdated implements Event{

	private boolean success;

	public InvoiceTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
