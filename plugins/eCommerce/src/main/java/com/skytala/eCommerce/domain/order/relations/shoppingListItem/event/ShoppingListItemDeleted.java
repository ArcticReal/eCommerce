package com.skytala.eCommerce.domain.order.relations.shoppingListItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingListItem.model.ShoppingListItem;
public class ShoppingListItemDeleted implements Event{

	private boolean success;

	public ShoppingListItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
