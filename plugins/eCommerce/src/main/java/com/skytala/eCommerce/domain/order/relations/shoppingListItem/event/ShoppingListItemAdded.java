package com.skytala.eCommerce.domain.order.relations.shoppingListItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingListItem.model.ShoppingListItem;
public class ShoppingListItemAdded implements Event{

	private ShoppingListItem addedShoppingListItem;
	private boolean success;

	public ShoppingListItemAdded(ShoppingListItem addedShoppingListItem, boolean success){
		this.addedShoppingListItem = addedShoppingListItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShoppingListItem getAddedShoppingListItem() {
		return addedShoppingListItem;
	}

}
