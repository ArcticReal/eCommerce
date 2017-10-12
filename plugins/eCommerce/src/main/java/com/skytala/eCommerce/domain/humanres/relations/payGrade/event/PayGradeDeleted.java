package com.skytala.eCommerce.domain.humanres.relations.payGrade.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.payGrade.model.PayGrade;
public class PayGradeDeleted implements Event{

	private boolean success;

	public PayGradeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
