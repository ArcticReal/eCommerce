package com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.model.CartAbandonedLine;
public class CartAbandonedLineDeleted implements Event{

	private boolean success;

	public CartAbandonedLineDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
