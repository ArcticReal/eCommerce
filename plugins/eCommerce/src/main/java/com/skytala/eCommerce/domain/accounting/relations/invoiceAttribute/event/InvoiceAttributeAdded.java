package com.skytala.eCommerce.domain.accounting.relations.invoiceAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceAttribute.model.InvoiceAttribute;
public class InvoiceAttributeAdded implements Event{

	private InvoiceAttribute addedInvoiceAttribute;
	private boolean success;

	public InvoiceAttributeAdded(InvoiceAttribute addedInvoiceAttribute, boolean success){
		this.addedInvoiceAttribute = addedInvoiceAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceAttribute getAddedInvoiceAttribute() {
		return addedInvoiceAttribute;
	}

}
