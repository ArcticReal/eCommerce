package com.skytala.eCommerce.domain.order.relations.quote.event.note;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.quote.model.note.QuoteNote;
public class QuoteNoteFound implements Event{

	private List<QuoteNote> quoteNotes;

	public QuoteNoteFound(List<QuoteNote> quoteNotes) {
		this.quoteNotes = quoteNotes;
	}

	public List<QuoteNote> getQuoteNotes()	{
		return quoteNotes;
	}

}
