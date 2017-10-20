package com.skytala.eCommerce.domain.order.relations.custRequest.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.note.CustRequestNote;
public class CustRequestNoteAdded implements Event{

	private CustRequestNote addedCustRequestNote;
	private boolean success;

	public CustRequestNoteAdded(CustRequestNote addedCustRequestNote, boolean success){
		this.addedCustRequestNote = addedCustRequestNote;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestNote getAddedCustRequestNote() {
		return addedCustRequestNote;
	}

}
