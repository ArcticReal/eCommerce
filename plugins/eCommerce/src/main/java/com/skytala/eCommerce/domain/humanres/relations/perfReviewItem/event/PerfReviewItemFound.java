package com.skytala.eCommerce.domain.humanres.relations.perfReviewItem.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.perfReviewItem.model.PerfReviewItem;
public class PerfReviewItemFound implements Event{

	private List<PerfReviewItem> perfReviewItems;

	public PerfReviewItemFound(List<PerfReviewItem> perfReviewItems) {
		this.perfReviewItems = perfReviewItems;
	}

	public List<PerfReviewItem> getPerfReviewItems()	{
		return perfReviewItems;
	}

}
