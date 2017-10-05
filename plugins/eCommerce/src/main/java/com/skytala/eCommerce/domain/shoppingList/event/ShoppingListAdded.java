package com.skytala.eCommerce.domain.shoppingList.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shoppingList.model.ShoppingList;
public class ShoppingListAdded implements Event{

	private ShoppingList addedShoppingList;
	private boolean success;

	public ShoppingListAdded(ShoppingList addedShoppingList, boolean success){
		this.addedShoppingList = addedShoppingList;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShoppingList getAddedShoppingList() {
		return addedShoppingList;
	}

}
