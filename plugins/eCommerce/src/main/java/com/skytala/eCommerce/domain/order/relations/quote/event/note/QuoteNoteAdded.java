package com.skytala.eCommerce.domain.order.relations.quote.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.note.QuoteNote;
public class QuoteNoteAdded implements Event{

	private QuoteNote addedQuoteNote;
	private boolean success;

	public QuoteNoteAdded(QuoteNote addedQuoteNote, boolean success){
		this.addedQuoteNote = addedQuoteNote;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public QuoteNote getAddedQuoteNote() {
		return addedQuoteNote;
	}

}
