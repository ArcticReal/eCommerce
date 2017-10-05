package com.skytala.eCommerce.domain.budgetReviewResultType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.budgetReviewResultType.model.BudgetReviewResultType;
public class BudgetReviewResultTypeUpdated implements Event{

	private boolean success;

	public BudgetReviewResultTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
