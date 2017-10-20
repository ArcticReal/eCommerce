package com.skytala.eCommerce.domain.party.relations.party.event.note;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.note.PartyNote;
public class PartyNoteFound implements Event{

	private List<PartyNote> partyNotes;

	public PartyNoteFound(List<PartyNote> partyNotes) {
		this.partyNotes = partyNotes;
	}

	public List<PartyNote> getPartyNotes()	{
		return partyNotes;
	}

}
