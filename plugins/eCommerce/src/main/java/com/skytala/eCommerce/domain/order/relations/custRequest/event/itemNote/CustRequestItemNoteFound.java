package com.skytala.eCommerce.domain.order.relations.custRequest.event.itemNote;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.itemNote.CustRequestItemNote;
public class CustRequestItemNoteFound implements Event{

	private List<CustRequestItemNote> custRequestItemNotes;

	public CustRequestItemNoteFound(List<CustRequestItemNote> custRequestItemNotes) {
		this.custRequestItemNotes = custRequestItemNotes;
	}

	public List<CustRequestItemNote> getCustRequestItemNotes()	{
		return custRequestItemNotes;
	}

}
