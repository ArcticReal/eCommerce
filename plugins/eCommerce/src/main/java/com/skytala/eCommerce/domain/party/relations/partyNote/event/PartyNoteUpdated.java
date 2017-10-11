package com.skytala.eCommerce.domain.party.relations.partyNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyNote.model.PartyNote;
public class PartyNoteUpdated implements Event{

	private boolean success;

	public PartyNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
