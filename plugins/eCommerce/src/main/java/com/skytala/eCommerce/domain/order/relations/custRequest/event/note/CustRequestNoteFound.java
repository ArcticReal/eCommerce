package com.skytala.eCommerce.domain.order.relations.custRequest.event.note;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.note.CustRequestNote;
public class CustRequestNoteFound implements Event{

	private List<CustRequestNote> custRequestNotes;

	public CustRequestNoteFound(List<CustRequestNote> custRequestNotes) {
		this.custRequestNotes = custRequestNotes;
	}

	public List<CustRequestNote> getCustRequestNotes()	{
		return custRequestNotes;
	}

}
