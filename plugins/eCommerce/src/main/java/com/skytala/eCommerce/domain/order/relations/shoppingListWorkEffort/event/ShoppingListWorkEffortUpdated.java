package com.skytala.eCommerce.domain.order.relations.shoppingListWorkEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingListWorkEffort.model.ShoppingListWorkEffort;
public class ShoppingListWorkEffortUpdated implements Event{

	private boolean success;

	public ShoppingListWorkEffortUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
