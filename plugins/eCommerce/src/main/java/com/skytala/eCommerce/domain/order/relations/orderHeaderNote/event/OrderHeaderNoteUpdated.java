package com.skytala.eCommerce.domain.order.relations.orderHeaderNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.model.OrderHeaderNote;
public class OrderHeaderNoteUpdated implements Event{

	private boolean success;

	public OrderHeaderNoteUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
