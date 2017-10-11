package com.skytala.eCommerce.domain.order.relations.returnItemTypeMap.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemTypeMap.model.ReturnItemTypeMap;
public class ReturnItemTypeMapFound implements Event{

	private List<ReturnItemTypeMap> returnItemTypeMaps;

	public ReturnItemTypeMapFound(List<ReturnItemTypeMap> returnItemTypeMaps) {
		this.returnItemTypeMaps = returnItemTypeMaps;
	}

	public List<ReturnItemTypeMap> getReturnItemTypeMaps()	{
		return returnItemTypeMaps;
	}

}
