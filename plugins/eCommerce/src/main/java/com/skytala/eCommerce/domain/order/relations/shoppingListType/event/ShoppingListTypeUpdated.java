package com.skytala.eCommerce.domain.order.relations.shoppingListType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingListType.model.ShoppingListType;
public class ShoppingListTypeUpdated implements Event{

	private boolean success;

	public ShoppingListTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
