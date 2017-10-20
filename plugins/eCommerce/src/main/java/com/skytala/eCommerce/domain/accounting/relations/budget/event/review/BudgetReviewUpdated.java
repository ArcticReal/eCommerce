package com.skytala.eCommerce.domain.accounting.relations.budget.event.review;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.review.BudgetReview;
public class BudgetReviewUpdated implements Event{

	private boolean success;

	public BudgetReviewUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
