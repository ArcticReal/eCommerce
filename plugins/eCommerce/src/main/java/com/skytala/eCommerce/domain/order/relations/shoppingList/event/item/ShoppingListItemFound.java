package com.skytala.eCommerce.domain.order.relations.shoppingList.event.item;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.item.ShoppingListItem;
public class ShoppingListItemFound implements Event{

	private List<ShoppingListItem> shoppingListItems;

	public ShoppingListItemFound(List<ShoppingListItem> shoppingListItems) {
		this.shoppingListItems = shoppingListItems;
	}

	public List<ShoppingListItem> getShoppingListItems()	{
		return shoppingListItems;
	}

}
