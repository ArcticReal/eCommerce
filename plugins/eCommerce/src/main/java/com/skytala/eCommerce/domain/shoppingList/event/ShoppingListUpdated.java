package com.skytala.eCommerce.domain.shoppingList.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shoppingList.model.ShoppingList;
public class ShoppingListUpdated implements Event{

	private boolean success;

	public ShoppingListUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
