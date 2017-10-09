package com.skytala.eCommerce.domain.payGrade.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.payGrade.model.PayGrade;
public class PayGradeUpdated implements Event{

	private boolean success;

	public PayGradeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}