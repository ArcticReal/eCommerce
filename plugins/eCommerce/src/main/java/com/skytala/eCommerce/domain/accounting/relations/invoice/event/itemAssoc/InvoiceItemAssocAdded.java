package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssoc.InvoiceItemAssoc;
public class InvoiceItemAssocAdded implements Event{

	private InvoiceItemAssoc addedInvoiceItemAssoc;
	private boolean success;

	public InvoiceItemAssocAdded(InvoiceItemAssoc addedInvoiceItemAssoc, boolean success){
		this.addedInvoiceItemAssoc = addedInvoiceItemAssoc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceItemAssoc getAddedInvoiceItemAssoc() {
		return addedInvoiceItemAssoc;
	}

}
