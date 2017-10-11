package com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.model.CartAbandonedLine;
public class CartAbandonedLineFound implements Event{

	private List<CartAbandonedLine> cartAbandonedLines;

	public CartAbandonedLineFound(List<CartAbandonedLine> cartAbandonedLines) {
		this.cartAbandonedLines = cartAbandonedLines;
	}

	public List<CartAbandonedLine> getCartAbandonedLines()	{
		return cartAbandonedLines;
	}

}
