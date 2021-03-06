package com.skytala.eCommerce.domain.order.relations.custRequest.event.status;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.status.CustRequestStatus;
public class CustRequestStatusFound implements Event{

	private List<CustRequestStatus> custRequestStatuss;

	public CustRequestStatusFound(List<CustRequestStatus> custRequestStatuss) {
		this.custRequestStatuss = custRequestStatuss;
	}

	public List<CustRequestStatus> getCustRequestStatuss()	{
		return custRequestStatuss;
	}

}
