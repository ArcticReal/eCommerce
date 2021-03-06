package com.skytala.eCommerce.domain.order.relations.shoppingList.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.ShoppingList;
public class ShoppingListFound implements Event{

	private List<ShoppingList> shoppingLists;

	public ShoppingListFound(List<ShoppingList> shoppingLists) {
		this.shoppingLists = shoppingLists;
	}

	public List<ShoppingList> getShoppingLists()	{
		return shoppingLists;
	}

}
