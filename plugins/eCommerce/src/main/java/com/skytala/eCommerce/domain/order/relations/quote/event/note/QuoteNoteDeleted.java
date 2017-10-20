package com.skytala.eCommerce.domain.order.relations.quote.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.note.QuoteNote;
public class QuoteNoteDeleted implements Event{

	private boolean success;

	public QuoteNoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
