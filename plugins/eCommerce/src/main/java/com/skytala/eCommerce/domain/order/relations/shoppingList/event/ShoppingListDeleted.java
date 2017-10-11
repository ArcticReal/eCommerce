package com.skytala.eCommerce.domain.order.relations.shoppingList.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.ShoppingList;
public class ShoppingListDeleted implements Event{

	private boolean success;

	public ShoppingListDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
