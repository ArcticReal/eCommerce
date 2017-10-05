package com.skytala.eCommerce.domain.budgetReview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.budgetReview.model.BudgetReview;
public class BudgetReviewUpdated implements Event{

	private boolean success;

	public BudgetReviewUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
