package com.skytala.eCommerce.domain.accounting.relations.invoice.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.typeAttr.InvoiceTypeAttr;
public class InvoiceTypeAttrAdded implements Event{

	private InvoiceTypeAttr addedInvoiceTypeAttr;
	private boolean success;

	public InvoiceTypeAttrAdded(InvoiceTypeAttr addedInvoiceTypeAttr, boolean success){
		this.addedInvoiceTypeAttr = addedInvoiceTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceTypeAttr getAddedInvoiceTypeAttr() {
		return addedInvoiceTypeAttr;
	}

}
