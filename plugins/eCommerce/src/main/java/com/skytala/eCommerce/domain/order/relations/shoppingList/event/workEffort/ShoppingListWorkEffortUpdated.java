package com.skytala.eCommerce.domain.order.relations.shoppingList.event.workEffort;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.workEffort.ShoppingListWorkEffort;
public class ShoppingListWorkEffortUpdated implements Event{

	private boolean success;

	public ShoppingListWorkEffortUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
