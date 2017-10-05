package com.skytala.eCommerce.domain.custRequest.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.custRequest.model.CustRequest;
public class CustRequestFound implements Event{

	private List<CustRequest> custRequests;

	public CustRequestFound(List<CustRequest> custRequests) {
		this.custRequests = custRequests;
	}

	public List<CustRequest> getCustRequests()	{
		return custRequests;
	}

}
