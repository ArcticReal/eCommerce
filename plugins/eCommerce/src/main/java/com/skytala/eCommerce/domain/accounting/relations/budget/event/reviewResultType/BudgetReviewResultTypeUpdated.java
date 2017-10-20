package com.skytala.eCommerce.domain.accounting.relations.budget.event.reviewResultType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.reviewResultType.BudgetReviewResultType;
public class BudgetReviewResultTypeUpdated implements Event{

	private boolean success;

	public BudgetReviewResultTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
