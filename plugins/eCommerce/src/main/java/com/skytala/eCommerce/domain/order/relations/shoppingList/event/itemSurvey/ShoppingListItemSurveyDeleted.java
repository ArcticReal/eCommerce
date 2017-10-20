package com.skytala.eCommerce.domain.order.relations.shoppingList.event.itemSurvey;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.itemSurvey.ShoppingListItemSurvey;
public class ShoppingListItemSurveyDeleted implements Event{

	private boolean success;

	public ShoppingListItemSurveyDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
