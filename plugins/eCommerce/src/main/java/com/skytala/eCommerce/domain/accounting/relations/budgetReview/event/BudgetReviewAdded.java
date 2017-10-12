package com.skytala.eCommerce.domain.accounting.relations.budgetReview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetReview.model.BudgetReview;
public class BudgetReviewAdded implements Event{

	private BudgetReview addedBudgetReview;
	private boolean success;

	public BudgetReviewAdded(BudgetReview addedBudgetReview, boolean success){
		this.addedBudgetReview = addedBudgetReview;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BudgetReview getAddedBudgetReview() {
		return addedBudgetReview;
	}

}
