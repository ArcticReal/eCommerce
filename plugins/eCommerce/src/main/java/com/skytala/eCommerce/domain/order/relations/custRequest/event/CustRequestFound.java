package com.skytala.eCommerce.domain.order.relations.custRequest.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.CustRequest;
public class CustRequestFound implements Event{

	private List<CustRequest> custRequests;

	public CustRequestFound(List<CustRequest> custRequests) {
		this.custRequests = custRequests;
	}

	public List<CustRequest> getCustRequests()	{
		return custRequests;
	}

}
