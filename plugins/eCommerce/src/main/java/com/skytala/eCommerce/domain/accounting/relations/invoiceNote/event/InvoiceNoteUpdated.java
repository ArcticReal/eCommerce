package com.skytala.eCommerce.domain.accounting.relations.invoiceNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.model.InvoiceNote;
public class InvoiceNoteUpdated implements Event{

	private boolean success;

	public InvoiceNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
