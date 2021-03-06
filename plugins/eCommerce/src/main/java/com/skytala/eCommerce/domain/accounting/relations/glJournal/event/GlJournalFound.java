package com.skytala.eCommerce.domain.accounting.relations.glJournal.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glJournal.model.GlJournal;
public class GlJournalFound implements Event{

	private List<GlJournal> glJournals;

	public GlJournalFound(List<GlJournal> glJournals) {
		this.glJournals = glJournals;
	}

	public List<GlJournal> getGlJournals()	{
		return glJournals;
	}

}
