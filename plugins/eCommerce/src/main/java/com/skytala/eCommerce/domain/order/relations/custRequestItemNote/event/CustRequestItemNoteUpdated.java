package com.skytala.eCommerce.domain.order.relations.custRequestItemNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.model.CustRequestItemNote;
public class CustRequestItemNoteUpdated implements Event{

	private boolean success;

	public CustRequestItemNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
