package com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.model.InvoiceTerm;
public class InvoiceTermAdded implements Event{

	private InvoiceTerm addedInvoiceTerm;
	private boolean success;

	public InvoiceTermAdded(InvoiceTerm addedInvoiceTerm, boolean success){
		this.addedInvoiceTerm = addedInvoiceTerm;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceTerm getAddedInvoiceTerm() {
		return addedInvoiceTerm;
	}

}
