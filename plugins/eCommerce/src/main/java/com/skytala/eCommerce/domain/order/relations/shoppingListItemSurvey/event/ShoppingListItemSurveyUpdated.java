package com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.model.ShoppingListItemSurvey;
public class ShoppingListItemSurveyUpdated implements Event{

	private boolean success;

	public ShoppingListItemSurveyUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
