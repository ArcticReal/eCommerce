package com.skytala.eCommerce.domain.accounting.relations.invoice.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoice.model.note.InvoiceNote;
public class InvoiceNoteUpdated implements Event{

	private boolean success;

	public InvoiceNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
