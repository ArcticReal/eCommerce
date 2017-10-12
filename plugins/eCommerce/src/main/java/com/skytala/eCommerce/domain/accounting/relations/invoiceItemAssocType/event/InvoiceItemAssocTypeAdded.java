package com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssocType.model.InvoiceItemAssocType;
public class InvoiceItemAssocTypeAdded implements Event{

	private InvoiceItemAssocType addedInvoiceItemAssocType;
	private boolean success;

	public InvoiceItemAssocTypeAdded(InvoiceItemAssocType addedInvoiceItemAssocType, boolean success){
		this.addedInvoiceItemAssocType = addedInvoiceItemAssocType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceItemAssocType getAddedInvoiceItemAssocType() {
		return addedInvoiceItemAssocType;
	}

}
