package com.skytala.eCommerce.domain.order.relations.returnHeader.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnHeader.model.ReturnHeader;
public class ReturnHeaderFound implements Event{

	private List<ReturnHeader> returnHeaders;

	public ReturnHeaderFound(List<ReturnHeader> returnHeaders) {
		this.returnHeaders = returnHeaders;
	}

	public List<ReturnHeader> getReturnHeaders()	{
		return returnHeaders;
	}

}
