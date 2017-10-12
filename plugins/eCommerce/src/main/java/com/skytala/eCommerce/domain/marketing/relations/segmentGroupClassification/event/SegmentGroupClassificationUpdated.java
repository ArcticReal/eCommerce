package com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.model.SegmentGroupClassification;
public class SegmentGroupClassificationUpdated implements Event{

	private boolean success;

	public SegmentGroupClassificationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
