package com.skytala.eCommerce.domain.accounting.relations.invoice.event.note;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.note.InvoiceNote;
public class InvoiceNoteFound implements Event{

	private List<InvoiceNote> invoiceNotes;

	public InvoiceNoteFound(List<InvoiceNote> invoiceNotes) {
		this.invoiceNotes = invoiceNotes;
	}

	public List<InvoiceNote> getInvoiceNotes()	{
		return invoiceNotes;
	}

}
