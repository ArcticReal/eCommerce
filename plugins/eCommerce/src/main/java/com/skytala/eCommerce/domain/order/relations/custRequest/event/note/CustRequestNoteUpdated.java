package com.skytala.eCommerce.domain.order.relations.custRequest.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.note.CustRequestNote;
public class CustRequestNoteUpdated implements Event{

	private boolean success;

	public CustRequestNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
