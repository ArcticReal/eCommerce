package com.skytala.eCommerce.domain.order.relations.custRequest.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.note.CustRequestNote;
public class CustRequestNoteDeleted implements Event{

	private boolean success;

	public CustRequestNoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
