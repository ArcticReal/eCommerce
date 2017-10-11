package com.skytala.eCommerce.domain.order.relations.shoppingListWorkEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingListWorkEffort.model.ShoppingListWorkEffort;
public class ShoppingListWorkEffortDeleted implements Event{

	private boolean success;

	public ShoppingListWorkEffortDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
