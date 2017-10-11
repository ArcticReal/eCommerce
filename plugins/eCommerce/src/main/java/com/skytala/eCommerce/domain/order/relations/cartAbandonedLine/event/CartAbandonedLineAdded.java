package com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.model.CartAbandonedLine;
public class CartAbandonedLineAdded implements Event{

	private CartAbandonedLine addedCartAbandonedLine;
	private boolean success;

	public CartAbandonedLineAdded(CartAbandonedLine addedCartAbandonedLine, boolean success){
		this.addedCartAbandonedLine = addedCartAbandonedLine;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CartAbandonedLine getAddedCartAbandonedLine() {
		return addedCartAbandonedLine;
	}

}
