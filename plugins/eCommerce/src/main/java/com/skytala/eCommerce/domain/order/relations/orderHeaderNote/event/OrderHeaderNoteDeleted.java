package com.skytala.eCommerce.domain.order.relations.orderHeaderNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.model.OrderHeaderNote;
public class OrderHeaderNoteDeleted implements Event{

	private boolean success;

	public OrderHeaderNoteDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
