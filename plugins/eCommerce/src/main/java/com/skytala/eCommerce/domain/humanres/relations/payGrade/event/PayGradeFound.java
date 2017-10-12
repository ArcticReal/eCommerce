package com.skytala.eCommerce.domain.humanres.relations.payGrade.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.payGrade.model.PayGrade;
public class PayGradeFound implements Event{

	private List<PayGrade> payGrades;

	public PayGradeFound(List<PayGrade> payGrades) {
		this.payGrades = payGrades;
	}

	public List<PayGrade> getPayGrades()	{
		return payGrades;
	}

}
