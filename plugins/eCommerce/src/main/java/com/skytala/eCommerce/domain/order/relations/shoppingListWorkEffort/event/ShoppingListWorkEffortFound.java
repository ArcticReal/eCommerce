package com.skytala.eCommerce.domain.order.relations.shoppingListWorkEffort.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingListWorkEffort.model.ShoppingListWorkEffort;
public class ShoppingListWorkEffortFound implements Event{

	private List<ShoppingListWorkEffort> shoppingListWorkEfforts;

	public ShoppingListWorkEffortFound(List<ShoppingListWorkEffort> shoppingListWorkEfforts) {
		this.shoppingListWorkEfforts = shoppingListWorkEfforts;
	}

	public List<ShoppingListWorkEffort> getShoppingListWorkEfforts()	{
		return shoppingListWorkEfforts;
	}

}
