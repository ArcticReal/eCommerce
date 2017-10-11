package com.skytala.eCommerce.domain.order.relations.shoppingListItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingListItem.model.ShoppingListItem;
public class ShoppingListItemUpdated implements Event{

	private boolean success;

	public ShoppingListItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
