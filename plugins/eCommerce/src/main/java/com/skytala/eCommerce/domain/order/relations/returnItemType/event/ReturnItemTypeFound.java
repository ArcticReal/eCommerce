package com.skytala.eCommerce.domain.order.relations.returnItemType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemType.model.ReturnItemType;
public class ReturnItemTypeFound implements Event{

	private List<ReturnItemType> returnItemTypes;

	public ReturnItemTypeFound(List<ReturnItemType> returnItemTypes) {
		this.returnItemTypes = returnItemTypes;
	}

	public List<ReturnItemType> getReturnItemTypes()	{
		return returnItemTypes;
	}

}