package com.skytala.eCommerce.domain.shoppingListType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shoppingListType.model.ShoppingListType;
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
