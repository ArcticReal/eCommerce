package com.skytala.eCommerce.domain.shoppingListType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shoppingListType.model.ShoppingListType;
public class ShoppingListTypeDeleted implements Event{

	private boolean success;

	public ShoppingListTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
