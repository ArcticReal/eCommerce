package com.skytala.eCommerce.domain.accounting.relations.budget.event.reviewResultType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.reviewResultType.BudgetReviewResultType;
public class BudgetReviewResultTypeAdded implements Event{

	private BudgetReviewResultType addedBudgetReviewResultType;
	private boolean success;

	public BudgetReviewResultTypeAdded(BudgetReviewResultType addedBudgetReviewResultType, boolean success){
		this.addedBudgetReviewResultType = addedBudgetReviewResultType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetReviewResultType getAddedBudgetReviewResultType() {
		return addedBudgetReviewResultType;
	}

}
