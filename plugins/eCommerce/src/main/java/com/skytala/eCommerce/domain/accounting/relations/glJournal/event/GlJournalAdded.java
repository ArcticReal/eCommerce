package com.skytala.eCommerce.domain.accounting.relations.glJournal.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glJournal.model.GlJournal;
public class GlJournalAdded implements Event{

	private GlJournal addedGlJournal;
	private boolean success;

	public GlJournalAdded(GlJournal addedGlJournal, boolean success){
		this.addedGlJournal = addedGlJournal;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlJournal getAddedGlJournal() {
		return addedGlJournal;
	}

}
