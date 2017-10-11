package com.skytala.eCommerce.domain.order.relations.orderHeaderNote.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.model.OrderHeaderNote;
public class OrderHeaderNoteAdded implements Event{

	private OrderHeaderNote addedOrderHeaderNote;
	private boolean success;

	public OrderHeaderNoteAdded(OrderHeaderNote addedOrderHeaderNote, boolean success){
		this.addedOrderHeaderNote = addedOrderHeaderNote;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderHeaderNote getAddedOrderHeaderNote() {
		return addedOrderHeaderNote;
	}

}
