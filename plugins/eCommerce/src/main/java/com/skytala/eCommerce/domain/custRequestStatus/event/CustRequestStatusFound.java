package com.skytala.eCommerce.domain.custRequestStatus.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.custRequestStatus.model.CustRequestStatus;
public class CustRequestStatusFound implements Event{

	private List<CustRequestStatus> custRequestStatuss;

	public CustRequestStatusFound(List<CustRequestStatus> custRequestStatuss) {
		this.custRequestStatuss = custRequestStatuss;
	}

	public List<CustRequestStatus> getCustRequestStatuss()	{
		return custRequestStatuss;
	}

}
