package com.skytala.eCommerce.domain.perfReview.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.perfReview.model.PerfReview;
public class PerfReviewFound implements Event{

	private List<PerfReview> perfReviews;

	public PerfReviewFound(List<PerfReview> perfReviews) {
		this.perfReviews = perfReviews;
	}

	public List<PerfReview> getPerfReviews()	{
		return perfReviews;
	}

}
