package com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAttribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAttribute.InvoiceItemAttribute;
public class InvoiceItemAttributeAdded implements Event{

	private InvoiceItemAttribute addedInvoiceItemAttribute;
	private boolean success;

	public InvoiceItemAttributeAdded(InvoiceItemAttribute addedInvoiceItemAttribute, boolean success){
		this.addedInvoiceItemAttribute = addedInvoiceItemAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceItemAttribute getAddedInvoiceItemAttribute() {
		return addedInvoiceItemAttribute;
	}

}
