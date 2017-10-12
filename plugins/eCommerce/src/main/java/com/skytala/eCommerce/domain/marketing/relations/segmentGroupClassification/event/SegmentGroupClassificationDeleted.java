package com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.model.SegmentGroupClassification;
public class SegmentGroupClassificationDeleted implements Event{

	private boolean success;

	public SegmentGroupClassificationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
