package com.skytala.eCommerce.domain.order.relations.custRequestNote.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestNote.model.CustRequestNote;
public class CustRequestNoteFound implements Event{

	private List<CustRequestNote> custRequestNotes;

	public CustRequestNoteFound(List<CustRequestNote> custRequestNotes) {
		this.custRequestNotes = custRequestNotes;
	}

	public List<CustRequestNote> getCustRequestNotes()	{
		return custRequestNotes;
	}

}
