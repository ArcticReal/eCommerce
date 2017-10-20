package com.skytala.eCommerce.domain.order.relations.shoppingList.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.type.ShoppingListType;
public class ShoppingListTypeAdded implements Event{

	private ShoppingListType addedShoppingListType;
	private boolean success;

	public ShoppingListTypeAdded(ShoppingListType addedShoppingListType, boolean success){
		this.addedShoppingListType = addedShoppingListType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShoppingListType getAddedShoppingListType() {
		return addedShoppingListType;
	}

}
