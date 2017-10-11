package com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.model.ShoppingListItemSurvey;
public class ShoppingListItemSurveyFound implements Event{

	private List<ShoppingListItemSurvey> shoppingListItemSurveys;

	public ShoppingListItemSurveyFound(List<ShoppingListItemSurvey> shoppingListItemSurveys) {
		this.shoppingListItemSurveys = shoppingListItemSurveys;
	}

	public List<ShoppingListItemSurvey> getShoppingListItemSurveys()	{
		return shoppingListItemSurveys;
	}

}
