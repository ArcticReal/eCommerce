package com.skytala.eCommerce.domain.custRequestType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.custRequestType.model.CustRequestType;
public class CustRequestTypeFound implements Event{

	private List<CustRequestType> custRequestTypes;

	public CustRequestTypeFound(List<CustRequestType> custRequestTypes) {
		this.custRequestTypes = custRequestTypes;
	}

	public List<CustRequestType> getCustRequestTypes()	{
		return custRequestTypes;
	}

}
