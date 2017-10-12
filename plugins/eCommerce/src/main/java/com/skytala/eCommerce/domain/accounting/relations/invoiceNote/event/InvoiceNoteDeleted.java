package com.skytala.eCommerce.domain.accounting.relations.invoiceNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.model.InvoiceNote;
public class InvoiceNoteDeleted implements Event{

	private boolean success;

	public InvoiceNoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
