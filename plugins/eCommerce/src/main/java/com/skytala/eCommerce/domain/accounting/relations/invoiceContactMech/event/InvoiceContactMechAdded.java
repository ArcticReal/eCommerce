package com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceContactMech.model.InvoiceContactMech;
public class InvoiceContactMechAdded implements Event{

	private InvoiceContactMech addedInvoiceContactMech;
	private boolean success;

	public InvoiceContactMechAdded(InvoiceContactMech addedInvoiceContactMech, boolean success){
		this.addedInvoiceContactMech = addedInvoiceContactMech;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceContactMech getAddedInvoiceContactMech() {
		return addedInvoiceContactMech;
	}

}
