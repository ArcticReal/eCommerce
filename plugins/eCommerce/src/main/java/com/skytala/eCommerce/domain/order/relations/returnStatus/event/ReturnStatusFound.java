package com.skytala.eCommerce.domain.order.relations.returnStatus.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnStatus.model.ReturnStatus;
public class ReturnStatusFound implements Event{

	private List<ReturnStatus> returnStatuss;

	public ReturnStatusFound(List<ReturnStatus> returnStatuss) {
		this.returnStatuss = returnStatuss;
	}

	public List<ReturnStatus> getReturnStatuss()	{
		return returnStatuss;
	}

}
