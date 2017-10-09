package com.skytala.eCommerce.domain.budgetReview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.budgetReview.model.BudgetReview;
public class BudgetReviewDeleted implements Event{

	private boolean success;

	public BudgetReviewDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}