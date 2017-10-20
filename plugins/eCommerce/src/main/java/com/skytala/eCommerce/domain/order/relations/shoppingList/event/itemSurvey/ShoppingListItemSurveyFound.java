package com.skytala.eCommerce.domain.order.relations.shoppingList.event.itemSurvey;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingList.model.itemSurvey.ShoppingListItemSurvey;
public class ShoppingListItemSurveyFound implements Event{

	private List<ShoppingListItemSurvey> shoppingListItemSurveys;

	public ShoppingListItemSurveyFound(List<ShoppingListItemSurvey> shoppingListItemSurveys) {
		this.shoppingListItemSurveys = shoppingListItemSurveys;
	}

	public List<ShoppingListItemSurvey> getShoppingListItemSurveys()	{
		return shoppingListItemSurveys;
	}

}
