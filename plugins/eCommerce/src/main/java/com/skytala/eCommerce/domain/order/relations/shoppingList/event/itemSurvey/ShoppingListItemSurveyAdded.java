package com.skytala.eCommerce.domain.order.relations.shoppingList.event.itemSurvey;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.itemSurvey.ShoppingListItemSurvey;
public class ShoppingListItemSurveyAdded implements Event{

	private ShoppingListItemSurvey addedShoppingListItemSurvey;
	private boolean success;

	public ShoppingListItemSurveyAdded(ShoppingListItemSurvey addedShoppingListItemSurvey, boolean success){
		this.addedShoppingListItemSurvey = addedShoppingListItemSurvey;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShoppingListItemSurvey getAddedShoppingListItemSurvey() {
		return addedShoppingListItemSurvey;
	}

}
