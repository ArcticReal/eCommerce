package com.skytala.eCommerce.domain.order.relations.shoppingList.event.workEffort;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.workEffort.ShoppingListWorkEffort;
public class ShoppingListWorkEffortFound implements Event{

	private List<ShoppingListWorkEffort> shoppingListWorkEfforts;

	public ShoppingListWorkEffortFound(List<ShoppingListWorkEffort> shoppingListWorkEfforts) {
		this.shoppingListWorkEfforts = shoppingListWorkEfforts;
	}

	public List<ShoppingListWorkEffort> getShoppingListWorkEfforts()	{
		return shoppingListWorkEfforts;
	}

}
