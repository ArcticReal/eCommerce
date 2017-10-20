package com.skytala.eCommerce.domain.accounting.relations.budget.event.reviewResultType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.reviewResultType.BudgetReviewResultType;
public class BudgetReviewResultTypeDeleted implements Event{

	private boolean success;

	public BudgetReviewResultTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
