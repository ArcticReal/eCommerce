package com.skytala.eCommerce.domain.order.relations.orderHeader.event.note;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeader.model.note.OrderHeaderNote;
public class OrderHeaderNoteDeleted implements Event{

	private boolean success;

	public OrderHeaderNoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
