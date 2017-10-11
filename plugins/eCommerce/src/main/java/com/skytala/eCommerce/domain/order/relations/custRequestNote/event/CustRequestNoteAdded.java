package com.skytala.eCommerce.domain.order.relations.custRequestNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestNote.model.CustRequestNote;
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
