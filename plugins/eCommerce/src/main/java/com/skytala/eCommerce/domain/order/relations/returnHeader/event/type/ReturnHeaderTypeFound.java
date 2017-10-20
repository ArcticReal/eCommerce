package com.skytala.eCommerce.domain.order.relations.returnHeader.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnHeader.model.type.ReturnHeaderType;
public class ReturnHeaderTypeFound implements Event{

	private List<ReturnHeaderType> returnHeaderTypes;

	public ReturnHeaderTypeFound(List<ReturnHeaderType> returnHeaderTypes) {
		this.returnHeaderTypes = returnHeaderTypes;
	}

	public List<ReturnHeaderType> getReturnHeaderTypes()	{
		return returnHeaderTypes;
	}

}
