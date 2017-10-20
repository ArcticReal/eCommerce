package com.skytala.eCommerce.domain.party.relations.party.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.note.PartyNote;
public class PartyNoteUpdated implements Event{

	private boolean success;

	public PartyNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
