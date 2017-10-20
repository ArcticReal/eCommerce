package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.classification;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.classification.SegmentGroupClassification;
public class SegmentGroupClassificationDeleted implements Event{

	private boolean success;

	public SegmentGroupClassificationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
