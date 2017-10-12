package com.skytala.eCommerce.domain.accounting.relations.budgetReview.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budgetReview.model.BudgetReview;
public class BudgetReviewFound implements Event{

	private List<BudgetReview> budgetReviews;

	public BudgetReviewFound(List<BudgetReview> budgetReviews) {
		this.budgetReviews = budgetReviews;
	}

	public List<BudgetReview> getBudgetReviews()	{
		return budgetReviews;
	}

}
