package com.skytala.eCommerce.domain.party.relations.partyNote.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyNote.model.PartyNote;
public class PartyNoteFound implements Event{

	private List<PartyNote> partyNotes;

	public PartyNoteFound(List<PartyNote> partyNotes) {
		this.partyNotes = partyNotes;
	}

	public List<PartyNote> getPartyNotes()	{
		return partyNotes;
	}

}
