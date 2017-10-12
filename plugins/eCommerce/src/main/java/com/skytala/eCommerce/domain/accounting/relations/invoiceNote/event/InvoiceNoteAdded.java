package com.skytala.eCommerce.domain.accounting.relations.invoiceNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.model.InvoiceNote;
public class InvoiceNoteAdded implements Event{

	private InvoiceNote addedInvoiceNote;
	private boolean success;

	public InvoiceNoteAdded(InvoiceNote addedInvoiceNote, boolean success){
		this.addedInvoiceNote = addedInvoiceNote;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InvoiceNote getAddedInvoiceNote() {
		return addedInvoiceNote;
	}

}
