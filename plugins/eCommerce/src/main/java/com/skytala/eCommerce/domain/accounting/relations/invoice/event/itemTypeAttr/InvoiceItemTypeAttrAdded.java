package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeAttr.InvoiceItemTypeAttr;
public class InvoiceItemTypeAttrAdded implements Event{

	private InvoiceItemTypeAttr addedInvoiceItemTypeAttr;
	private boolean success;

	public InvoiceItemTypeAttrAdded(InvoiceItemTypeAttr addedInvoiceItemTypeAttr, boolean success){
		this.addedInvoiceItemTypeAttr = addedInvoiceItemTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceItemTypeAttr getAddedInvoiceItemTypeAttr() {
		return addedInvoiceItemTypeAttr;
	}

}
