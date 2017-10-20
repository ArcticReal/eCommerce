package com.skytala.eCommerce.domain.accounting.relations.budget.event.reviewResultType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.reviewResultType.BudgetReviewResultType;
public class BudgetReviewResultTypeFound implements Event{

	private List<BudgetReviewResultType> budgetReviewResultTypes;

	public BudgetReviewResultTypeFound(List<BudgetReviewResultType> budgetReviewResultTypes) {
		this.budgetReviewResultTypes = budgetReviewResultTypes;
	}

	public List<BudgetReviewResultType> getBudgetReviewResultTypes()	{
		return budgetReviewResultTypes;
	}

}
