package com.skytala.eCommerce.domain.shoppingListType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shoppingListType.model.ShoppingListType;
public class ShoppingListTypeFound implements Event{

	private List<ShoppingListType> shoppingListTypes;

	public ShoppingListTypeFound(List<ShoppingListType> shoppingListTypes) {
		this.shoppingListTypes = shoppingListTypes;
	}

	public List<ShoppingListType> getShoppingListTypes()	{
		return shoppingListTypes;
	}

}
