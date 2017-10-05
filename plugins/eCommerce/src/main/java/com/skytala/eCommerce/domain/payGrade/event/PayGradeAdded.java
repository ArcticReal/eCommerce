package com.skytala.eCommerce.domain.payGrade.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.payGrade.model.PayGrade;
public class PayGradeAdded implements Event{

	private PayGrade addedPayGrade;
	private boolean success;

	public PayGradeAdded(PayGrade addedPayGrade, boolean success){
		this.addedPayGrade = addedPayGrade;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PayGrade getAddedPayGrade() {
		return addedPayGrade;
	}

}
