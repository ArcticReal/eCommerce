package com.skytala.eCommerce.domain.returnReason.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.returnReason.model.ReturnReason;
public class ReturnReasonFound implements Event{

	private List<ReturnReason> returnReasons;

	public ReturnReasonFound(List<ReturnReason> returnReasons) {
		this.returnReasons = returnReasons;
	}

	public List<ReturnReason> getReturnReasons()	{
		return returnReasons;
	}

}
