package com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceTermAttribute.model.InvoiceTermAttribute;
public class InvoiceTermAttributeAdded implements Event{

	private InvoiceTermAttribute addedInvoiceTermAttribute;
	private boolean success;

	public InvoiceTermAttributeAdded(InvoiceTermAttribute addedInvoiceTermAttribute, boolean success){
		this.addedInvoiceTermAttribute = addedInvoiceTermAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceTermAttribute getAddedInvoiceTermAttribute() {
		return addedInvoiceTermAttribute;
	}

}
