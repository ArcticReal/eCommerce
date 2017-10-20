package com.skytala.eCommerce.domain.order.relations.shoppingList.event.workEffort;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.workEffort.ShoppingListWorkEffort;
public class ShoppingListWorkEffortDeleted implements Event{

	private boolean success;

	public ShoppingListWorkEffortDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
