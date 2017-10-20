package com.skytala.eCommerce.domain.party.relations.party.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.note.PartyNote;
public class PartyNoteAdded implements Event{

	private PartyNote addedPartyNote;
	private boolean success;

	public PartyNoteAdded(PartyNote addedPartyNote, boolean success){
		this.addedPartyNote = addedPartyNote;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyNote getAddedPartyNote() {
		return addedPartyNote;
	}

}
