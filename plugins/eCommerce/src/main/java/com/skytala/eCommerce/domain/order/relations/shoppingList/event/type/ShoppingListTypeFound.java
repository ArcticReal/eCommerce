package com.skytala.eCommerce.domain.order.relations.shoppingList.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.type.ShoppingListType;
public class ShoppingListTypeFound implements Event{

	private List<ShoppingListType> shoppingListTypes;

	public ShoppingListTypeFound(List<ShoppingListType> shoppingListTypes) {
		this.shoppingListTypes = shoppingListTypes;
	}

	public List<ShoppingListType> getShoppingListTypes()	{
		return shoppingListTypes;
	}

}
