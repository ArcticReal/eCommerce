package com.skytala.eCommerce.domain.order.relations.returnItem.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.ReturnItem;
public class ReturnItemFound implements Event{

	private List<ReturnItem> returnItems;

	public ReturnItemFound(List<ReturnItem> returnItems) {
		this.returnItems = returnItems;
	}

	public List<ReturnItem> getReturnItems()	{
		return returnItems;
	}

}
