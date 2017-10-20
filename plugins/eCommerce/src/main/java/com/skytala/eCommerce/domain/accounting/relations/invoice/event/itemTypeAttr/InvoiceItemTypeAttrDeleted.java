package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeAttr.InvoiceItemTypeAttr;
public class InvoiceItemTypeAttrDeleted implements Event{

	private boolean success;

	public InvoiceItemTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}