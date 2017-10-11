package com.skytala.eCommerce.domain.order.relations.custRequestItemNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.model.CustRequestItemNote;
public class CustRequestItemNoteAdded implements Event{

	private CustRequestItemNote addedCustRequestItemNote;
	private boolean success;

	public CustRequestItemNoteAdded(CustRequestItemNote addedCustRequestItemNote, boolean success){
		this.addedCustRequestItemNote = addedCustRequestItemNote;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CustRequestItemNote getAddedCustRequestItemNote() {
		return addedCustRequestItemNote;
	}

}
