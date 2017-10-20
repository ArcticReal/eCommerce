package com.skytala.eCommerce.domain.order.relations.returnItem.event.typeMap;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.typeMap.ReturnItemTypeMap;
public class ReturnItemTypeMapFound implements Event{

	private List<ReturnItemTypeMap> returnItemTypeMaps;

	public ReturnItemTypeMapFound(List<ReturnItemTypeMap> returnItemTypeMaps) {
		this.returnItemTypeMaps = returnItemTypeMaps;
	}

	public List<ReturnItemTypeMap> getReturnItemTypeMaps()	{
		return returnItemTypeMaps;
	}

}
