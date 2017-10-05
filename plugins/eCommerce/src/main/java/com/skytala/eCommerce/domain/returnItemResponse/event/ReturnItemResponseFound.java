package com.skytala.eCommerce.domain.returnItemResponse.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnItemResponse.model.ReturnItemResponse;
public class ReturnItemResponseFound implements Event{

	private List<ReturnItemResponse> returnItemResponses;

	public ReturnItemResponseFound(List<ReturnItemResponse> returnItemResponses) {
		this.returnItemResponses = returnItemResponses;
	}

	public List<ReturnItemResponse> getReturnItemResponses()	{
		return returnItemResponses;
	}

}
