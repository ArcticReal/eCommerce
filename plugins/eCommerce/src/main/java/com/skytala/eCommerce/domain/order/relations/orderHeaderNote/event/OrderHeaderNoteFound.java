package com.skytala.eCommerce.domain.order.relations.orderHeaderNote.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.model.OrderHeaderNote;
public class OrderHeaderNoteFound implements Event{

	private List<OrderHeaderNote> orderHeaderNotes;

	public OrderHeaderNoteFound(List<OrderHeaderNote> orderHeaderNotes) {
		this.orderHeaderNotes = orderHeaderNotes;
	}

	public List<OrderHeaderNote> getOrderHeaderNotes()	{
		return orderHeaderNotes;
	}

}
