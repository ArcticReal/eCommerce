package com.skytala.eCommerce.domain.humanres.relations.perfReviewItemType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfReviewItemType.model.PerfReviewItemType;
public class PerfReviewItemTypeFound implements Event{

	private List<PerfReviewItemType> perfReviewItemTypes;

	public PerfReviewItemTypeFound(List<PerfReviewItemType> perfReviewItemTypes) {
		this.perfReviewItemTypes = perfReviewItemTypes;
	}

	public List<PerfReviewItemType> getPerfReviewItemTypes()	{
		return perfReviewItemTypes;
	}

}