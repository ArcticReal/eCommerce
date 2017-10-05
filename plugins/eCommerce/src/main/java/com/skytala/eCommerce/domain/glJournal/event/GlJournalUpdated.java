package com.skytala.eCommerce.domain.glJournal.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glJournal.model.GlJournal;
public class GlJournalUpdated implements Event{

	private boolean success;

	public GlJournalUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
