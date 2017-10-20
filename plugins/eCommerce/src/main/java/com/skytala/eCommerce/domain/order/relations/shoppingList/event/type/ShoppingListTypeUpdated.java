package com.skytala.eCommerce.domain.order.relations.shoppingList.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.type.ShoppingListType;
public class ShoppingListTypeUpdated implements Event{

	private boolean success;

	public ShoppingListTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
