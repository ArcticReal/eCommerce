package com.skytala.eCommerce.domain.order.relations.shoppingList.event.workEffort;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.workEffort.ShoppingListWorkEffort;
public class ShoppingListWorkEffortAdded implements Event{

	private ShoppingListWorkEffort addedShoppingListWorkEffort;
	private boolean success;

	public ShoppingListWorkEffortAdded(ShoppingListWorkEffort addedShoppingListWorkEffort, boolean success){
		this.addedShoppingListWorkEffort = addedShoppingListWorkEffort;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShoppingListWorkEffort getAddedShoppingListWorkEffort() {
		return addedShoppingListWorkEffort;
	}

}
