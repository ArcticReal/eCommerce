package com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.model.BudgetReviewResultType;
public class BudgetReviewResultTypeDeleted implements Event{

	private boolean success;

	public BudgetReviewResultTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
