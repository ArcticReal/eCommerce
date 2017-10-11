package com.skytala.eCommerce.domain.order.relations.custRequestItemNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.model.CustRequestItemNote;
public class CustRequestItemNoteDeleted implements Event{

	private boolean success;

	public CustRequestItemNoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
