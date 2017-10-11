package com.skytala.eCommerce.domain.order.relations.quoteNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quoteNote.model.QuoteNote;
public class QuoteNoteUpdated implements Event{

	private boolean success;

	public QuoteNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
