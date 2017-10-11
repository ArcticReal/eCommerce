package com.skytala.eCommerce.domain.product.relations.reorderGuideline.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.reorderGuideline.model.ReorderGuideline;
public class ReorderGuidelineFound implements Event{

	private List<ReorderGuideline> reorderGuidelines;

	public ReorderGuidelineFound(List<ReorderGuideline> reorderGuidelines) {
		this.reorderGuidelines = reorderGuidelines;
	}

	public List<ReorderGuideline> getReorderGuidelines()	{
		return reorderGuidelines;
	}

}
